package org.example.algorithm.stack;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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
        String str = "3+3/2*2-3-4/4*2*9";
        logger.info("公式:{}", str);
        char[] chars = str.toCharArray();

        // 低级运算符,反之高级
        Set<Character> lowSings = new HashSet<>();
        lowSings.add('+');
        lowSings.add('-');

        ArrayStack<BigDecimal> num = new ArrayStack<>(10);
        ArrayStack<String> sign = new ArrayStack<>(10);

        for (int i = 0, size = chars.length; i < size; i++) {
            char aChar = chars[i];
            String s = String.valueOf(aChar);
            boolean isNum = Character.isDigit(aChar);
            if (isNum) {
                num.push(new BigDecimal(s));
            }
            else {
                // 取出顶部栈
                String as = sign.pop();
                // 如果是空表示第一次加入 直接压栈 跳过本次循环
                if (StringUtils.isBlank(as)) {
                    sign.push(s);
                    continue;
                }
                sign.push(as);

                // 如果 只有一个数字,则先压栈 跳过本次循环
                if (num.count() == 1) {
                    sign.push(s);
                    continue;
                }

                // 如果 当前循环 运算符号为低级运算
                if (lowSings.contains(aChar)) {
                    BigDecimal i2 = num.pop();
                    BigDecimal i1 = num.pop();
                    String signStr = sign.pop();
                    BigDecimal value = math(i1, i2, signStr);
                    logger.info("计算:{}, {}, {} = {}", i1, signStr, i2, value);
                    num.push(value);
                    sign.push(s);
                    continue;
                }
                else {
                    String prevSign = sign.pop();
                    if (!lowSings.contains(prevSign.charAt(0))) {
                        BigDecimal i2 = num.pop();
                        BigDecimal i1 = num.pop();
                        BigDecimal value = math(i1, i2, prevSign);
                        logger.info("计算:{}, {}, {} = {}", i1, prevSign, i2, value);
                        num.push(value);
                    } else {
                        sign.push(prevSign);
                    }
                    sign.push(s);
                }
            }

            // 最后一个字符了
            if (i == size - 1 && isNum) {
                String signStr;
                while (StringUtils.isNoneBlank(signStr = sign.pop())) {
                    String prevSign = sign.pop();
                    if (lowSings.contains(signStr.charAt(0)) && StringUtils.isNotBlank(prevSign)) {
                        BigDecimal i2 = num.pop();
                        BigDecimal i1 = num.pop();
                        BigDecimal value = math(i1, i2, signStr);
                        num.push(value);
                        sign.push(signStr);
                        logger.info("计算:{}, {}, {} = {}", i1, signStr, i2, value);
                    } else {
                        BigDecimal i2 = num.pop();
                        BigDecimal i1 = num.pop();
                        BigDecimal value = math(i1, i2, signStr);
                        logger.info("计算:{}, {}, {} = {}", i1, signStr, i2, value);
                        num.push(value);
                        sign.push(prevSign);
                    }

                }

            }
        }
        System.out.println(num.pop());
    }

    public BigDecimal math(BigDecimal i1, BigDecimal i2, String sign) {
        if (sign.equals("+")) {
            return i1.add(i2);
        } else if (sign.equals("-")) {
            return i1.subtract(i2);
        } else if (sign.equals("*")) {
            return i1.multiply(i2);
        } else if (sign.equals("/")) {
            return i1.divide(i2, 2, BigDecimal.ROUND_HALF_UP);
        }
        return null;
    }


}
