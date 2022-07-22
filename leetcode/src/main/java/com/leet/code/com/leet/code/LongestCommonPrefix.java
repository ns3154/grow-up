package com.leet.code.com.leet.code;

import org.junit.jupiter.api.Test;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/07/08 20:01
 **/
public class LongestCommonPrefix {

    @Test
    public void run () {
        String[] a = new String[] {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(a));
    }


    public String longestCommonPrefix(String[] strs) {
        String sbs = strs[0];

        for (int i = 1,j = strs.length; i < j; i++) {
            if (sbs.length() == 0) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            String str = strs[i];
            int len = Math.min(sbs.length(), str.length());
            char[] ts = sbs.toCharArray();
            char[] ms = str.toCharArray();
            for (int z = 0; z < len; z++) {
                if (ts[z] != ms[z]) {
                    break;
                }
                sb.append(ts[z]);
            }
            sbs = sb.toString();
        }
        return sbs.toString();
    }


}
