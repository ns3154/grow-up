package com.example.demo;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/09/24 21:10
 **/
public class WxTest {

    public static void main(String[] args) {
        Integer[] array = {1,2,3,4,5,6,5,3,4,2,1};
        for (int i = 0;i < array.length;i++) {
            Integer current = array[i];
            String binary = Integer.toBinaryString(current);
            int b = 4 - binary.length();
            for (int t = 0; t < b;t++) {
                binary = "0" + binary;
            }
            System.out.println("数字:" + current + ",二进制位:" + binary);
        }

        System.out.println("--------------------------------------------------");

        int compare = array[0];
        for (int i = 1;i < array.length;i++) {
            int current = array[i];
            compare = compare ^ current;
            String afterCompareBinary =  toBinaryString(compare);
            System.out.println("比较:" + current + ",二进制:" + toBinaryString(current)+ ",比较后二进制:" + afterCompareBinary);
        }

        System.out.println("单个数字:" + Integer.toString(compare, 10));
    }

    public static String toBinaryString(int i) {
        String binary = Integer.toBinaryString(i);
        int b = 4 - binary.length();
        for (int t = 0; t < b;t++) {
            binary = "0" + binary;
        }
        return binary;
    }



    public static void s(int z, int t) {
        String s = null;
        if (z == 1) {
            s = "z1";
        } else if (z == 2){
            s = "z2";
        }else {
            s = "zelse";
        }

        if (t == 1) {
            s += "t1";
        } else if (t == 2) {
            s += "t2";
        } else {
            s += "teles";
        }

        System.out.println(s);

    }



}
