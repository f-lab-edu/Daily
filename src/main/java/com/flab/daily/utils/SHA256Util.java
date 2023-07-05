package com.flab.daily.utils;

import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SHA256Util {

    public static String encryptSalt(String password, String salt)
    {
        return encrypt(password + salt);
    }

    public static String encrypt(String password)
    {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            String hax = new String(Hex.encode(hash));
            return hax;
        }
        catch (Exception e) {
            throw new Error(e);
        }
    }


    public static Boolean match(String origin, String target) {
        try {
            if (origin.equals(encrypt(target))) {
                return true;
            };
            return false;
        }
        catch (Exception e) {
            throw new Error(e);
        }
    }

    public static String notLibraryEncrypt(String password)
    {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        }
        catch (Exception e) {
            throw new Error(e);
        }
    }
}
