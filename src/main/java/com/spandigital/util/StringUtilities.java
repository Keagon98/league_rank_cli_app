package com.spandigital.util;

public class StringUtilities {

    public static String removeNumbersFromString(String string) {
        return string.replaceAll("[^A-Za-z ]+", "").trim();
    }

    public static String removeCharsFromString(String string) {
        return string.replaceAll("[^0-9]", "");
    }
}
