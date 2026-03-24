package com.example.concurrent;

public class Atomic64 {

    private static long a = -1L;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override public void run() {
                while (true) {
                    a = 1L;
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override public void run() {
                while (true) {
                    a = -1L;
                }
            }
        }).start();


        while (true) {
            long t = a;
            String str = toBinary(t);
            if (!str.equals("0000000000000000000000000000000000000000000000000000000000000001") &&
            !str.equals("1111111111111111111111111111111111111111111111111111111111111111")) {
                System.err.println(a);
                System.err.println(toBinary(a));
            }
        }

    }



    private static String toBinary(long l) {
        StringBuilder sb = new StringBuilder(Long.toBinaryString(l));
        while (sb.length() < 64) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }


}
