package org.example.algorithm.stack;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2020/04/19 17:28
 **/
public class Test {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final Pattern pattern = Pattern.compile("^[\\d]*$");


    @org.junit.Test
    public void check() {
        ArrayStack<String> arrayStack = new ArrayStack<>(10);
        arrayStack.push("a");
        arrayStack.push("b");
        arrayStack.push("c");

        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());

        arrayStack.push("d");
        arrayStack.push("e");
        arrayStack.push("f");

        System.out.println(arrayStack.pop());

        System.out.println(math(new BigDecimal("3"), new BigDecimal("4"), "/").doubleValue());
    }

    // 有bug 有时间再改
    @org.junit.Test
    public  void math() {
//        String content = "3+3/2*2-3-4/4*2*9";
        String content = "1+2+3*4";
        ArrayStack<BigDecimal> num = new ArrayStack<>(10);
        ArrayStack<String> sign = new ArrayStack<>(10);
        Set<String> lowSigns = new HashSet<>();
        lowSigns.add("-");
        lowSigns.add("+");
        int length = content.length();
        for (int i = 0; i < length; i++) {
            String str = content.substring(i, i + 1);

            if (isNumeric(str)) {
                num.push(new BigDecimal(str));
            } else {
                sign.push(str);
            }

            if (num.size() > 2 && sign.size() > 2) {
                String topSign = sign.pop();
                if (lowSigns.contains(topSign)) {
                    String signStr = sign.pop();
                    BigDecimal b3 = num.pop();
                    BigDecimal b2 = num.pop();
                    BigDecimal b1 = num.pop();
                    BigDecimal value;
                    if (lowSigns.contains(signStr)) {
                        value = math(b1, b2, sign.pop());
                        value = math(value, b3, signStr);
                    } else {
                        value = math(b2, b3, signStr);
                        value = math(b1, value, sign.pop());
                    }
                    num.push(value);
                } else {
                    String needSign = sign.pop();
                    if (!lowSigns.contains(needSign)) {
                        BigDecimal b3 = num.pop();
                        BigDecimal b2 = num.pop();
                        BigDecimal value = math(b2, b3, needSign);
                        num.push(value);
                    } else {
                        BigDecimal b3 = num.pop();
                        BigDecimal b2 = num.pop();
                        BigDecimal b1 = num.pop();
                        BigDecimal value = math(b1, b2, sign.pop());
                        num.push(value);
                        num.push(b3);
                        sign.push(needSign);
                    }

                }
                sign.push(topSign);
            }
        }
        System.out.println(num.pop());



    }

    private BigDecimal math(BigDecimal i1, BigDecimal i2, String sign) {
        switch (sign) {
            case "+":
                return i1.add(i2);
            case "-":
                return i1.subtract(i2);
            case "*":
                return i1.multiply(i2);
            case "/":
                return i1.divide(i2, 2, BigDecimal.ROUND_HALF_UP);
            default:
                return null;
        }
    }

    private boolean isNumeric(String str) {
        return pattern.matcher(str).matches();
    }

}
