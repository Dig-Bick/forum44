package com.example.forum4.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String encode(String str) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        md.update(str.getBytes());
        String result = new BigInteger(1, md.digest()).toString(16);

        while (result.length() < 32) {
            result = "0" + result;
        }

        return result;
    }
}
