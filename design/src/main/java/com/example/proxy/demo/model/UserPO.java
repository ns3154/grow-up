package com.example.proxy.demo.model;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/21 14:01
 **/
public class UserPO {

    private Long id;

    private String name;

    private Integer sex;

    public UserPO() {

    }

    private UserPO(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setSex(builder.sex);
    }


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

    public static final class Builder {
        private Long id;
        private String name;
        private Integer sex;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder sex(Integer val) {
            sex = val;
            return this;
        }

        public UserPO build() {
            return new UserPO(this);
        }
    }

    @Override
    public String toString() {
        return "UserPO{" + "id=" + id + ", name='" + name + '\'' + ", sex=" + sex + '}';
    }
}
