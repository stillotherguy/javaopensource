package com.rabbit.spring;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * Created by Ethan Zhang on 15/6/4.
 */
public class UtilTest {
    @Test
    public void testTokenizeToStringArray() {
        String str = "1,,2,,3";
        System.out.println(Arrays.toString(StringUtils.tokenizeToStringArray(str, ",")));
    }
}
