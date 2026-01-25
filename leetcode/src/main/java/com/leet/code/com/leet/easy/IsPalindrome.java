package com.leet.code.com.leet.easy;


public class IsPalindrome {

    public void run() {
        String s = "0P";
        System.err.println(isPalindrome(s));

    }

    // 65 - 90
    // 97 - 127
    public boolean isPalindrome(String s) {
        if (s.length() == 1) {
            return true;
        }

        int left = 0;
        int right = s.length() - 1;
        boolean b = true;
        while (left < s.length() && right >= 0) {
            char l = s.charAt(left);
            char r = s.charAt(right);

            if (!(65 <= l && l <= 90) && !(97 <= l && l <= 127) && l != '0') {
                left++;
                continue;
            }

            if (!(65 <= r && r <= 90) && !(97 <= r && r <= 127) && r != '0') {
                right--;
                continue;
            }



            if (l != r && Math.abs(l - r) != 32 || l == '0' || r == '0') {
                b = false;
                break;
            }
            left++;
            right--;

        }

        return b;
    }

}
