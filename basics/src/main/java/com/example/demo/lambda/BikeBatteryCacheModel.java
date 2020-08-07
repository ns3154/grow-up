package com.example.demo.lambda;

import java.util.List;
import java.util.function.Function;

/**
 * <pre>
 *      共享电动车电池更换标准 顶级类
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/07/27 17:11
 **/
public class BikeBatteryCacheModel {

    /**
     * 缓存过期时间 时间错:毫秒
     */
    private final long expireTime;

    /**
     * 换电标准列表
     * 注:注入数据时 请按照数据顺序
     */
    private final List<BikeBatteryStandardModel> bikeBatteryStandards;

    /**
     * 标准电量边界
     * 45-35-25
     */
    private final String powerStr;

    public BikeBatteryCacheModel(long expireTime, List<BikeBatteryStandardModel> bikeBatteryStandards, String str) {
        this.expireTime = expireTime;
        this.bikeBatteryStandards = bikeBatteryStandards;
        this.powerStr = str;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public List<BikeBatteryStandardModel> getBikeBatteryStandards() {
        return bikeBatteryStandards;
    }

    public String getPowerStr() {
        return powerStr;
    }

}
