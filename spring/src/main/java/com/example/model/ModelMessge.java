package com.example.model;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/23 19:15
 **/
public class ModelMessge<T> {

    private String message;

    private Integer code;

    private T data;


    public ModelMessge() {
        // nothing
    }

    public ModelMessge<T> ok(T data) {
        this.code = 200;
        this.message = "ok";
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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
}
