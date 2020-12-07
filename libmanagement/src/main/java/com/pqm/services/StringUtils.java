/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pqm.services;

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
    public static String createUserId(String surName, String firstName){
        int i = 0;
        surName = surName.trim();
        surName = surName.replaceAll("\\s+"," ");
        firstName = firstName.trim();
        firstName = firstName.replaceAll("\\s+"," ");
        String kq = firstName + '.' + Character.toString(surName.charAt(i));
        for(i = 1; i < surName.length(); i++)
        {
            if(Character.toString(surName.charAt(i)).equals(" "))
                kq += Character.toString(surName.charAt(i+1));
        }
        return kq.toLowerCase();
    }
}
