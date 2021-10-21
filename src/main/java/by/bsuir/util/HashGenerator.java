package by.bsuir.util;

import org.apache.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
    private final static Logger logger = Logger.getLogger(HashGenerator.class);
    private static final String NAME = "MD5";

    public String hash(String password){
        byte[] bytes = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(NAME);
            bytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b: bytes){
            stringBuilder.append(String.format("%x",b));
        }
        return stringBuilder.toString();
    }
}
