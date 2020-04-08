package com.example.model.vo;

import java.math.BigDecimal;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/08 16:43
 **/
public class UserVO {

    private Long userId;

    private String userName;

    private Integer age;

    private String sex;

    private String createTime;

    private Double balance;

    private BigDecimal bdd;

    private Short ss;

    private Character ccc;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public BigDecimal getBdd() {
        return bdd;
    }

    public void setBdd(BigDecimal bdd) {
        this.bdd = bdd;
    }

    public Short getSs() {
        return ss;
    }

    public void setSs(Short ss) {
        this.ss = ss;
    }

    public Character getCcc() {
        return ccc;
    }

    public void setCcc(Character ccc) {
        this.ccc = ccc;
    }
}
