/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pqm.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 *
 * @author user
 */
public class StringUtils {

    public static String removeAccent(String s) {

        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public static String createUserId(String surName, String firstName) {
        int i = 0;
        surName = surName.trim();
        surName = surName.replaceAll("\\s+", " ");
        firstName = firstName.trim();
        firstName = firstName.replaceAll("\\s+", " ");
        String kq = firstName + '.' + Character.toString(surName.charAt(i));
        for (i = 1; i < surName.length(); i++) {
            if (Character.toString(surName.charAt(i)).equals(" ")) {
                kq += Character.toString(surName.charAt(i + 1));
            }
        }
        return kq.toLowerCase();
    }

    public static String standardizedString(String s) {
        s = s.trim();
        s = s.replaceAll("\\s+", " ");
        String temp[] = s.split(" ");
        s = "";
        for (int i = 0; i < temp.length; i++) {
            s += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1)
            {
                s += " ";
            }
        }
        return s;
    }

    //MD5
    public String convertHashToString(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(text.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
    public static boolean emailPattern(String email){
        String EMAIL_PATTERN = 
            "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
        return Pattern.matches(EMAIL_PATTERN, email);
    }

}
