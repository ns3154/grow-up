package com.example.model.bo;

import java.math.BigDecimal;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/08 18:10
 **/
public class UserBO {

    private Double balance;

    private BigDecimal bd;

    private Short s;

    private Character c;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public BigDecimal getBd() {
        return bd;
    }

    public void setBd(BigDecimal bd) {
        this.bd = bd;
    }

    public Short getS() {
        return s;
    }

    public void setS(Short s) {
        this.s = s;
    }

    public Character getC() {
        return c;
    }

    public void setC(Character c) {
        this.c = c;
    }
}
