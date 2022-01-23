package com.mirka.ms.model.util;

import org.mindrot.jbcrypt.BCrypt;

public class Encryptor {
    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public static boolean checkInputPassword(String password, String encryptedPassword) {
        return BCrypt.checkpw(password, encryptedPassword);
    }
}