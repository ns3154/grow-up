package com.example.model.bean;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/31 16:30
 **/
public class User {

    private Long id;

    private String userName;

    private Integer age;

    private User(Builder builder) {
        setId(builder.id);
        setUserName(builder.userName);
        setAge(builder.age);
    }

    public User() {

    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(User copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.userName = copy.getUserName();
        builder.age = copy.getAge();
        return builder;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    /**
     * {@code User} builder static inner class.
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    public static final class Builder {
        private Long id;
        private String userName;
        private Integer age;

        private Builder() {
        }

        /**
         * Sets the {@code id} and returns a reference to this Builder so that the methods can be chained together.
         * @param id the {@code id} to set
         * @return a reference to this Builder
         */
        public Builder withId(Long id) {
            this.id = id;
            return this;
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
         * Returns a {@code User} built from the parameters previously set.
         *
         * @return a {@code User} built with parameters of this {@code User.Builder}
         */
        public User build() {
            return new User(this);
        }
    }
}
