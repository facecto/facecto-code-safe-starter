package com.facecto.code.safe.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facecto.code.base.CodeResult;
import com.facecto.code.safe.SafeResult;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.facecto.code.safe.utils.CodeJSONUtils.hasJsonString;


/**
 * CryptUtils
 *
 * @author Jon So, https://cto.pub, https://facecto.com, https://github.com/facecto
 * @version v1.1.0 (2021/8/08)
 */
public class CodeCryptUtils {

    /**
     * encrypt map 2 string
     *
     * @param map
     * @param key
     * @param iv
     * @return result
     * @throws Exception
     */
    private static String encrypt2String(Map<String, Object> map, String key, String iv) throws Exception {
        String jsonString = JSONObject.toJSONString(map);
        if (!StringUtils.isEmpty(jsonString)) {
            return CodeAesUtils.encrypt(jsonString, key, iv);
        }
        return null;
    }

    /**
     * decrypt string 2 map
     *
     * @param data
     * @param key
     * @param iv
     * @return result
     * @throws Exception
     */
    private static Map<String, Object> decryptString2Map(String data, String key, String iv) throws Exception {
        String jsonString = CodeAesUtils.decrypt(data, key, iv);
        JSONObject jsonObject = JSON.parseObject(jsonString);
        Map<String, Object> map = JSONObject.toJavaObject(jsonObject, Map.class);
        return map;
    }

    /**
     * decrypt SafeResult to CodeResult
     *
     * @param result
     * @param key
     * @param iv
     * @return result
     * @throws Exception
     */
    public static CodeResult decryptData(SafeResult result, String key, String iv) throws Exception {
        Map<String, Object> map = decryptString2Map(result.getData(), key, iv);
        if (map.get("sign").equals(result.getSign())) {
            map.remove("sign");
            return CodeResult.ok(result.getMessage(), map);
        } else {
            return CodeResult.error("sign error!");
        }
    }

    /**
     * encrypt CodeResult 2 SafeResult
     *
     * @param result
     * @param key
     * @param iv
     * @param secret
     * @return result
     * @throws Exception
     */
    public static SafeResult encryptData(CodeResult result, String key, String iv, String secret) throws Exception {
        String json = JSONObject.toJSONString(result.getData());
        Map map;
        if (hasJsonString(json)) {
            JSONObject jsonObject = JSON.parseObject(json);
            map = JSONObject.toJavaObject(jsonObject, Map.class);
        } else {
            map = new HashMap();
            map.put("data", json);
        }
        String sign = sign(map, secret);
        map.put("sign", sign);
        String data = encrypt2String(map, key, iv);
        return SafeResult.ok(sign, data, result.getMessage());
    }

    /**
     * get sign
     *
     * @param data
     * @param secret
     * @return result
     * @throws NoSuchAlgorithmException
     */
    public static String sign(Map<String, Object> data, String secret) throws NoSuchAlgorithmException {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if ("v2sign".equals(k)) {
                continue;
            }
            Object v = data.get(k);
            if (v != null && !StringUtils.isEmpty(v.toString())) {
                sb.append(k).append("=").append(data.get(k).toString().trim()).append("&");
            }
        }
        sb.append("key=").append(secret);
        return getSha(sb.toString()).toUpperCase();
    }

    /**
     * verify sign
     *
     * @param sign
     * @param data
     * @param key
     * @param iv
     * @return result
     * @throws Exception
     */
    public static boolean signVerify(String sign, String data, String key, String iv) throws Exception {
        Map<String, Object> result = decryptString2Map(data, key, iv);
        return result.get("sign").equals(sign);
    }

    /**
     * get sha1
     *
     * @param txt
     * @return result
     * @throws NoSuchAlgorithmException
     */
    public static String getSha(String txt) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA");
        byte[] cipherBytes = messageDigest.digest(txt.getBytes());
        String cipherStr = Hex.encodeHexString(cipherBytes);
        return cipherStr;
    }

    /**
     * get md5
     *
     * @param txt
     * @return result
     * @throws NoSuchAlgorithmException
     */
    public static String getMd5(String txt) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] cipherBytes = messageDigest.digest(txt.getBytes());
        String cipherStr = Hex.encodeHexString(cipherBytes);
        return cipherStr;
    }
}
