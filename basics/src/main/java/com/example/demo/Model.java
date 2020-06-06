package com.example.demo;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/15 19:04
 **/
public class Model {

    public Model() {
        // nothing
    }

    public Model(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
