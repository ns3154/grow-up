package com.example.mvc.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.mvc.exception.MyException;
import com.example.mvc.utils.AesEncryptUtils;
import com.example.mvc.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.util.StreamUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东 (qq:397827222)
 * @since 1.0
 * @date 2019/05/08 10:30
 **/
public class MyHttpInputMessage implements HttpInputMessage {

    private HttpInputMessage inputMessage;

    public MyHttpInputMessage(HttpInputMessage inputMessage) {
        this.inputMessage = inputMessage;
    }

    /**
     * Return the body of the message as an input stream.
     * @return the input stream body (never {@code null})
     * @throws IOException in case of I/O errors
     */
    @Override
    public InputStream getBody() throws IOException {
        String content = StreamUtils.copyToString(inputMessage.getBody(), Charset.defaultCharset());
        JSONObject jsonObject = JSON.parseObject(content);
        if (null == jsonObject || jsonObject.isEmpty()) {
            throw new MyException(Constants.ConstantsEnum.ENCRYPTION_ERROR);
        }

        String data = jsonObject.getString("data");

        if (StringUtils.isBlank(data)) {
            throw new MyException(Constants.ConstantsEnum.ENCRYPTION_ERROR);
        }
        return new ByteArrayInputStream(AesEncryptUtils.decrypt(data).getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Return the headers of this message.
     * @return a corresponding HttpHeaders object (never {@code null})
     */
    @Override
    public HttpHeaders getHeaders() {
        return inputMessage.getHeaders();
    }

}
