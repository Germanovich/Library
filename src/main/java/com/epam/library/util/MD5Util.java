package com.epam.library.util;

import com.epam.library.exception.ApplicationException;
import com.epam.library.resource.ConfigurationManager;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    private MD5Util() {
    }

    /**
     * Md5 encode.
     *
     * @param password the password
     * @return the string
     * @throws ApplicationException the application exception
     */
    public static String md5Encode(String password) {
        MessageDigest messageDigest;
        byte[] digest;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(password.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new ApplicationException(ConfigurationManager.getProperty("message.error.encode"), e);
        }

        BigInteger bigInt = new BigInteger(1, digest);
        StringBuilder md5Hex = new StringBuilder(bigInt.toString(16));

        while (md5Hex.length() < 32) {
            md5Hex.insert(0, "0");
        }
        return md5Hex.toString();
    }
}
