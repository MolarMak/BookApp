package com.molarmak.bookapp.helper;

import java.security.SecureRandom;

/**
 * Created by Maxim on 1/21/18.
 */

public class GenerateToken {

    /**
     * function for generate secure book token as String
     * @return
     */

    public static String generate() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes.toString();
    }
}
