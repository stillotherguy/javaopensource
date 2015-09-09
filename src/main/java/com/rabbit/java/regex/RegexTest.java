package com.rabbit.java.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ethan Zhang
 **/
public class RegexTest {
    public static final Pattern pattern = Pattern.compile("([t])([e])");

    public static void main(String[] args) {
        String str = "test,,,,,test";
        final Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group());
        }

    }
}
