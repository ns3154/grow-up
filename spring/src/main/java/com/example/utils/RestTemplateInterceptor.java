package com.example.utils;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/12/29 17:04
 **/
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    public static final char SPLIT = '/';

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
//        request.getURI()
        ClientHttpResponse execute = execution.execute(request, body);
        return execute;
    }

    private static String getRequestURI(String url) {
        int length = url.length();
        StringBuilder sb = new StringBuilder(length);

        for (int index = 0; index < length;) {
            char c = url.charAt(index);

            if (c == SPLIT && index < length - 1) {
                sb.append(c);

                StringBuilder nextSection = new StringBuilder();
                boolean isNumber = false;
                boolean first = true;

                for (int j = index + 1; j < length; j++) {
                    char next = url.charAt(j);

                    if ((first || isNumber == true) && next != SPLIT) {
                        isNumber = isNumber(next);
                        first = false;
                    }

                    if (next == SPLIT) {
                        if (isNumber) {
                            sb.append("{num}");
                        } else {
                            sb.append(nextSection.toString());
                        }
                        index = j;

                        break;
                    } else if (j == length - 1) {
                        if (isNumber) {
                            sb.append("{num}");
                        } else {
                            nextSection.append(next);
                            sb.append(nextSection.toString());
                        }
                        index = j + 1;
                        break;
                    } else {
                        nextSection.append(next);
                    }
                }
            } else {
                sb.append(c);
                index++;
            }
        }

        return sb.toString();
    }

    private static boolean isNumber(char c) {
        return (c >= '0' && c <= '9') || c == '.' || c == '-' || c == ',';
    }

    public static void main(String[] args) {
        System.out.println(getRequestURI("https://api.mch.weixin.qq.com/v3/payscore/serviceorder/2412121212121212/cancel"));
    }
}
