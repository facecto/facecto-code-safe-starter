package com.facecto.code.safe.utils;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

/**
 * AESUtils
 *
 * @author Jon So, https://cto.pub, https://facecto.com, https://github.com/facecto
 * @version v1.1.0 (2021/8/08)
 */
public class CodeAesUtils {

    private static final String AES_NAME = "AES";
    private static final String ALGORITHM = "AES/CTR/NoPadding";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * encrypt
     *
     * @param source source
     * @param key    key
     * @param iv     iv
     * @return result
     * @throws Exception
     */
    public static String encrypt(String source, String key, String iv) throws Exception {
        byte[] data = null;
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), AES_NAME);
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);
        data = cipher.doFinal(source.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeBase64String(data);
    }


    /**
     * decrypt
     *
     * @param data encrypt data
     * @param key  key
     * @param iv   iv
     * @return result
     * @throws Exception
     */
    public static String decrypt(String data, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), AES_NAME);
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
        return new String(cipher.doFinal(Base64.decodeBase64(data)));
    }

}