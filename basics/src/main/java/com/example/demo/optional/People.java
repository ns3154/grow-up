package com.example.demo.optional;

import java.util.Optional;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/08/08 11:23
 **/
public class People {

    private Car car;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<Car> getCar() {
        return Optional.ofNullable(car);
    }


}
