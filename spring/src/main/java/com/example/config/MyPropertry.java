package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/08 14:19
 **/
@ConfigurationProperties(prefix = "test.user")
@Component
public class MyPropertry {

    static {
        System.out.println("myPropertry 实例化.....");
    }

    private Long id;

    private String name;

    private Integer sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "MyPropertry{" + "id=" + id + ", name='" + name + '\'' + ", sex=" + sex + '}';
    }
}
