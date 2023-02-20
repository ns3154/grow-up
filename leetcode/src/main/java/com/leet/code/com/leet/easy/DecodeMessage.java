package com.leet.code.com.leet.easy;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class DecodeMessage {

    @Test
    public void run () {
        String key = "the quick brown fox jumps over the lazy dog";
        String message = "vkbs bs t suepuv";

        System.out.println(decodeMessageV2(key, message));
    }

    public String decodeMessage(String key, String message) {
        StringBuilder sb = new StringBuilder();
        char cur = 'a';

        Map<Character, Character> m = new HashMap<>();

        for (char c : key.toCharArray()) {
            if (c != ' ' && null == m.get(c)) {
                m.put(c, cur++);
            }
        }

        for (char c : message.toCharArray()) {
            if (c == ' ') {
                sb.append(" ");
                continue;
            }
            sb.append(m.get(c));
        }

        return sb.toString();
    }

    public String decodeMessageV2(String key, String message) {
        StringBuilder sb = new StringBuilder();
        char[] chars = new char[128];
        char cur = 'a';
        for (char c : key.toCharArray()) {
            if (c != ' ' && chars[c] == '\0') {
                chars[c] = cur++;
            }
        }

        for (char c : message.toCharArray()) {
            if (c == ' ') {
                sb.append(" ");
                continue;
            }
            sb.append(chars[c]);
        }

        return sb.toString();
    }


}
