package org.example.binary;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/10/24 00:54
 **/
public class Test {

    public static void main(String[] args) {
        int[] arr = {1,1,2,2,3,3,3,4,4,4};

        // A ^ B
        int tem = 0;
        for (int i : arr) {
            tem ^= i;
        }


        int d = tem & (~tem + 1);

        int a = 0;
//        第一种
//        int b = 0;
//
//        for (int i : arr) {
//            if ((i & d) == d) {
//                a ^= i;
//            } else {
//                b ^= i;
//            }
//        }
//        System.out.println(a);
//        System.out.println(b);

        for (int i : arr) {
            if ((i & d) == 0) {
                a ^= i;
            }
        }

        System.out.println(tem ^ a);
        System.out.println(a);



    }




}

