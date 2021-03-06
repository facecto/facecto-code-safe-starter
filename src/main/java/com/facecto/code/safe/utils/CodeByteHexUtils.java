package com.facecto.code.safe.utils;

import javax.xml.bind.DatatypeConverter;

/**
 * ByteHexUtils
 *
 * @author Jon So, https://facecto.com, https://github.com/facecto
 * @version v1.1.0 (2021/8/08)
 */
public class CodeByteHexUtils {


    /**
     * HexString 2 byte[]
     *
     * @param hexString
     * @return result
     */
    public static byte[] getByteFromHexString(String hexString) {
        return DatatypeConverter.parseHexBinary(hexString);
    }

    /**
     * BASE64 String 2 byte[]
     *
     * @param base64String
     * @return result
     */
    public static byte[] getByteFromBase64String(String base64String) {
        return DatatypeConverter.parseBase64Binary(base64String);
    }


    /**
     * byte[] 2 HexString
     *
     * @param sourceByte
     * @return result
     */
    public static String getHexString(byte[] sourceByte) {
        return DatatypeConverter.printHexBinary(sourceByte);
    }

    /**
     * byte[] 2 BASE64 String
     *
     * @param sourceByte
     * @return result
     */
    public static String getBase64tring(byte[] sourceByte) {
        return DatatypeConverter.printBase64Binary(sourceByte);
    }
}
