package com.sayed.anthropology.data;

public class StringUtils {

    public static boolean hasText(String s) {
        return s != null && !s.isEmpty();
    }

    public static String shortenString(String str, int limit) {
        return hasText(str) && str.length() > limit ? str.substring(0, limit).concat("...") : str;
    }
}
