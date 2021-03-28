package com.example.model.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/23 19:07
 **/
public class UserDTO {

    public UserDTO() {
        // nothing
    }

    private String name;

    private Integer age;

    private Integer sex;

    private Date createTime;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public UserDTO setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
