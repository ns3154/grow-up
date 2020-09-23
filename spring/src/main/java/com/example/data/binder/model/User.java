package com.example.data.binder.model;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/09/23 15:53
 **/
public class User {

    private String name;

    private Integer age;

    private Boolean isMember;

    private UserSub userSub;

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

    public Boolean getMember() {
        return isMember;
    }

    public void setMember(Boolean member) {
        isMember = member;
    }

    public UserSub getUserSub() {
        return userSub;
    }

    public void setUserSub(UserSub userSub) {
        this.userSub = userSub;
    }


    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", age=" + age + ", isMember=" + isMember + ", userSub=" + userSub + '}';
    }
}
