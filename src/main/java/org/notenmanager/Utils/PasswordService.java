package org.notenmanager.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordService {

    static String HashAlgorithm = "MD5";

    public static String CreatePasswordFromUserData(String username, String plainTextPassword) {

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(HashAlgorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        messageDigest.update(Salt(username, plainTextPassword).getBytes());

        byte[] bytes = messageDigest.digest();

        StringBuffer HashBuffer = new StringBuffer();
        for (byte aByte : bytes) {
            HashBuffer.append(String.format("%02X", aByte));
        }

        return HashBuffer.toString();
    }

    public static String Salt(String salt, String plainTextPassword) {
        String saltedPassword;

        saltedPassword = salt +
                "-" +
                plainTextPassword.substring(0, plainTextPassword.length() / 2) +
                "-" +
                salt +
                "-" +
                plainTextPassword.substring(plainTextPassword.length() / 2, plainTextPassword.length()) +
                "-" +
                salt;


        return saltedPassword;
    }

}
