package com.example.demo.lambda;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/07/27 17:13
 **/
public class BikeBatteryStandardModel {

    private final double power;

    private final Integer batteryStatus;

    public BikeBatteryStandardModel(double power, Integer batteryStatus) {
        this.power = power;
        this.batteryStatus = batteryStatus;
    }


    public double getPower() {
        return power;
    }

    public Integer getBatteryStatus() {
        return batteryStatus;
    }



}
