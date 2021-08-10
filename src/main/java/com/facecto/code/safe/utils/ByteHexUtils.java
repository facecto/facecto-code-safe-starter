package com.facecto.code.safe.utils;

import javax.xml.bind.DatatypeConverter;

/**
 * @author Jon So
 * @author page https://cto.pub
 * @date 2021/5/01
 */
public class ByteHexUtils {


    /**
     * HexString 2 byte[]
     *
     * @param hexString
     * @return
     */
    public static byte[] getByteFromHexString(String hexString) {
        return DatatypeConverter.parseHexBinary(hexString);
    }

    /**
     * BASE64 String 2 byte[]
     *
     * @param base64String
     * @return
     */
    public static byte[] getByteFromBase64String(String base64String) {
        return DatatypeConverter.parseBase64Binary(base64String);
    }


    /**
     * byte[] 2 HexString
     *
     * @param sourceByte
     * @return
     */
    public static String getHexString(byte[] sourceByte) {
        return DatatypeConverter.printHexBinary(sourceByte);
    }

    /**
     * byte[] 2 BASE64 String
     *
     * @param sourceByte
     * @return
     */
    public static String getBase64tring(byte[] sourceByte) {
        return DatatypeConverter.printBase64Binary(sourceByte);
    }
}
