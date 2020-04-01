package com.example.model.dto;

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

    private UserDTO(Builder builder) {
        setName(builder.name);
        setAge(builder.age);
        setSex(builder.sex);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(UserDTO copy) {
        Builder builder = new Builder();
        builder.name = copy.getName();
        builder.age = copy.getAge();
        builder.sex = copy.getSex();
        return builder;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public static final class Builder {
        private String name;
        private Integer age;
        private Integer sex;

        private Builder() {
        }

        /**
         * Sets the {@code name} and returns a reference to this Builder so that the methods can be chained together.
         * @param name the {@code name} to set
         * @return a reference to this Builder
         */
        public Builder withName(String name) {
            this.name = name;
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
