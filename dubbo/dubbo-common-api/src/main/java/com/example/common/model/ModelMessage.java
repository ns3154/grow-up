package com.example.common.model;

import java.io.Serializable;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/23 11:03
 **/
public class ModelMessage<T> implements Serializable {

    private static final long serialVersionUID = 4744863571767409721L;

    private Integer code;

    private T data;

    private String message;



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
