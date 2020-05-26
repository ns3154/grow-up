package com.example.model.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.SmartInitializingSingleton;


/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/31 16:30
 **/
public class User implements SmartInitializingSingleton {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Long id;

    private String userName;

    private Integer age;

    private String desc;

    private User(Builder builder) {
        logger.error("user 实例化");
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName='" + userName + '\'' + ", age=" + age + ", desc='" + desc + '\'' + '}';
    }

    /**
     * 在单例预实例化阶段的末尾调用，并保证已经创建了所有常规的单例bean。ListableBeanFactory。
     * 该方法中的getBeansOfType调用不会在引导过程中引发意外的副作用。
     * 注意:这个回调不会在BeanFactory引导后按需延迟初始化单例bean，也不会在任何其他bean作用域触发。
     *      对于仅具有特定引导语义的bean，请小心使用它
     */
    @Override
    public void afterSingletonsInstantiated() {
        logger.error("user.afterSingletonsInstantiated()");
    }
}
