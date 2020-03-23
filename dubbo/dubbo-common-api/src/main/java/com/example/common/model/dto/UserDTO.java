package com.example.common.model.dto;

import java.io.Serializable;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/23 11:02
 **/
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -7999777699554915961L;

    private String userName;

    private Integer age;

    private Integer sex;

    public UserDTO() {
        // nothing
    }

    private UserDTO(Builder builder) {
        setUserName(builder.userName);
        setAge(builder.age);
        setSex(builder.sex);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(UserDTO copy) {
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
     * {@code UserDTO} builder static inner class.
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    public static final class Builder implements Serializable {
        private static final long serialVersionUID = 530685057293856529L;
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
         * Returns a {@code UserDTO} built from the parameters previously set.
         *
         * @return a {@code UserDTO} built with parameters of this {@code UserDTO.Builder}
         */
        public UserDTO build() {
            return new UserDTO(this);
        }
    }
}
