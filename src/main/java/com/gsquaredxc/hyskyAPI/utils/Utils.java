package com.gsquaredxc.hyskyAPI.utils;

import java.util.regex.Pattern;

public class Utils {
    public static Pattern specialFormattingCharacters = Pattern.compile("§[a-f0-9k-r]");

    public static boolean isModConnected(){
        return true;
    }

    public static String removeFormatting(final String toRemove){
        return specialFormattingCharacters.matcher(toRemove).replaceAll("");
    }
}
