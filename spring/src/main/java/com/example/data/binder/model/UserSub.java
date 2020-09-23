package com.example.data.binder.model;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/09/23 16:00
 **/
public class UserSub {

    private String phone;

    private String address;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserSub{" + "phone='" + phone + '\'' + ", address='" + address + '\'' + '}';
    }
}
