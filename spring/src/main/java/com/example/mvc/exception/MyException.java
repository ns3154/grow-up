package com.example.mvc.exception;


import com.example.utils.Constants;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2019/03/19 17:09
 **/
public class MyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;

    public int getCode() {
        return code;
    }

    public MyException(Constants.ConstantsEnum ce) {
        super(ce.getMessage());
        this.code = ce.getCode();
    }

    public MyException(int code, String msg) {
        super(msg);
        this.code = code;
    }


}
