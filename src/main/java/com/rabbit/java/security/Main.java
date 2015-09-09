package com.rabbit.java.security;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.*;
import java.security.*;
import java.security.Provider.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main<T> {
    static final String INPUT_PATH = "/Users/zhangjing/Code/github/javaopensource/src/main/java/com/rabbit/java/security/input.csv";
    static final String OUTPUT_MICRO_PATH = "/Users/zhangjing/Code/github/javaopensource/src/main/java/com/rabbit/java/security/output_micro.props";
    static final String OUTPUT_TRADITIONAL_PATH = "/Users/zhangjing/Code/github/javaopensource/src/main/java/com/rabbit/java/security/output_traditional.props";
    static final String PREFIX = "party_msr_limit.%s";
    static final String MSR = "msr";
    static final String POS = "pos";
    private T[] t = (T[]) new Object[2];

	public static void main(String[] args) throws IOException {
        FileInputStream fis = null;
        Scanner scanner = null;
        FileWriter micro = null;
        FileWriter trand = null;
        int totalNum = 0;
        int microNum = 0;
        int trandNum = 0;
        try {
            fis = new FileInputStream(INPUT_PATH);
            scanner = new Scanner(fis);
            micro = new FileWriter(new File(OUTPUT_MICRO_PATH), true);
            trand = new FileWriter(new File(OUTPUT_TRADITIONAL_PATH), true);
            String line = null;
            while (scanner.hasNextLine()) {
                totalNum++;
                line = scanner.nextLine();
                String[] datas = StringUtils.tokenizeToStringArray(line, ",", false, false);
                String partyId = datas[0];
                String license = datas[1];

                int realPosCnt = 0;
                if (!"NULL".equals(datas[2])) {
                    realPosCnt = Integer.parseInt(datas[2]);
                }
                int realMsrCnt = 0;
                if (!"NULL".equals(datas[3])) {
                    realMsrCnt = Integer.parseInt(datas[3]);
                }
                int realCnt = 0;
                if (!"NULL".equals(datas[4])) {
                    realCnt = Integer.parseInt(datas[4]);
                }

                int agPosCnt = 0;
                if (!"NULL".equals(datas[5])) {
                    agPosCnt = Integer.parseInt(datas[5]);
                }
                int agMsrCnt = 0;
                if (!"NULL".equals(datas[6])) {
                    agMsrCnt = Integer.parseInt(datas[6]);
                }
                int agCnt = 0;
                if (!"NULL".equals(datas[7])) {
                    agCnt = Integer.parseInt(datas[7]);
                }

                Assert.isTrue(realCnt == (realMsrCnt + realPosCnt));
                Assert.isTrue(agCnt == (agMsrCnt + agPosCnt));

                int count = agCnt > realCnt ? agCnt : realCnt;
                String key = String.format(PREFIX, partyId);
                if (isMicro(partyId)) {

                    if ((hasLicense(license) && count > 10) || (!hasLicense(license) && count >3)) {
                        micro.write(key + "=" + (agCnt > realCnt ? agCnt : realCnt) + "\n");
                        microNum++;
                    }


                } else {
                    if (count > 10) {
                        micro.write(key + "=" + (agCnt > realCnt ? agCnt : realCnt) + "\n");
                        trandNum++;
                    }


                }
            }
        } finally {
            fis.close();
            micro.close();
            trand.close();
            scanner.close();
        }
        System.out.println(totalNum);
        System.out.println(microNum);
        System.out.println(trandNum);

	}

    static boolean hasLicense(String license) {
        return !StringUtils.isEmpty(license) && !"NULL".equals(license);
    }

    static boolean isMicro(String partyId) {
        return "2222".equals(partyId.substring(3, 7));
    }

    static void test() {
        List<String> list = new ArrayList<String>();
        String s = list.get(0);
    }

}

class Main1<T> extends Main<T> {
    private T[] t = (T[]) new Object[2];
}
