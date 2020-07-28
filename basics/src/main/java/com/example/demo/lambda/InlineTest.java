package com.example.demo.lambda;

/**
 * <pre>
 *     内联
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/07/28 17:08
 **/
public class InlineTest {

    interface Call {
        String accept(String str);
    }

    static class Test {
        String string = "1,2,3,4";

        public String getStr(Call call) {
            return call.accept(string);
        }
    }


    public static void main(String[] args) {
        Test test = new Test();
        String str1 = test.getStr(str -> str.split(",")[0] + str.split(",")[1]);
        System.out.println(str1);
    }

}
