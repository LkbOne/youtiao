package com.example.phoebe.youtiao.commmon.util;

import org.apache.commons.codec.binary.Base64;

/**
 * @author doggy
 *         Created on 2017-01-02.
 */
public class Base64Util {
    public static String bytesToString(byte[] bs) {
        return Base64.encodeBase64URLSafeString(bs);
    }

    public static byte[] stringToBytes(String text) {
        return Base64.decodeBase64(text);
    }
}
