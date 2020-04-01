package com.example.model.dto;

import java.io.Serializable;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/23 16:06
 **/
public class UserToken implements Serializable {

    private static final long serialVersionUID = -2356291897847187706L;

    public UserToken() {
        // nothing
    }

    private String userName;

    private Integer age;

    private Integer sex;

    private UserToken(Builder builder) {
        setUserName(builder.userName);
        setAge(builder.age);
        setSex(builder.sex);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(UserToken copy) {
        Builder builder = new Builder();
        builder.userName = copy.getUserName();
        builder.age = copy.getAge();
        builder.sex = copy.getSex();
        return builder;
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * {@code UserToken} builder static inner class.
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    public static final class Builder {
        private String userName;
        private Integer age;
        private Integer sex;

        private Builder() {
        }

        /**
         * Sets the {@code userName} and returns a reference to this Builder so that the methods can be chained
         * together.
         * @param userName the {@code userName} to set
         * @return a reference to this Builder
         */
        public Builder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        /**
         * Sets the {@code age} and returns a reference to this Builder so that the methods can be chained together.
         * @param age the {@code age} to set
         * @return a reference to this Builder
         */
        public Builder withAge(Integer age) {
            this.age = age;
            return this;
        }

        /**
         * Sets the {@code sex} and returns a reference to this Builder so that the methods can be chained together.
         * @param sex the {@code sex} to set
         * @return a reference to this Builder
         */
        public Builder withSex(Integer sex) {
            this.sex = sex;
            return this;
        }

        /**
         * Returns a {@code UserToken} built from the parameters previously set.
         *
         * @return a {@code UserToken} built with parameters of this {@code UserToken.Builder}
         */
        public UserToken build() {
            return new UserToken(this);
        }
    }
}
