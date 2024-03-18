package com.housecallpro.pro.tests.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherUtil {

    public static String match(String s, String regex) {
        Matcher m = Pattern.compile(regex).matcher(s);
        if (m.find()) {
            return m.group(0);
        }
        return null;
    }
}
