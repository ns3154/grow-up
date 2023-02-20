package com.leet.code.com.leet.easy;

import org.junit.jupiter.api.Test;

public class LengthOfLastWord {

    @Test
    public void run () {
        System.out.println(lengthOfLastWord("asdfasf asdfasf sdfasf asdf sdf   12 "));
    }

    public int lengthOfLastWord(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int strLen = s.length() - 1;

        int len = 0;
        for (int i = strLen; i >=0; i--) {
            if (Character.isSpaceChar(s.charAt(i)) && len == 0) {
                continue;
            }

            if (Character.isSpaceChar(s.charAt(i))) {
                break;
            }

            len++;
        }
        return len;
    }




}
