package com.example.demo.lambda;

/**
 * Created by wanchao on 2018/1/26.
 * 电池状态枚举
 */
public enum LackPowerStatusEnum {
    normal(0,"正常"),
    low(100,"低电"),
    lack(200,"缺电"),
    pinch(300,"馈电"),
    zero(400,"无电"),
    ;

    LackPowerStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;

    }

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static LackPowerStatusEnum getEnum(Integer code){
        for(LackPowerStatusEnum scene : LackPowerStatusEnum.values()){
            if (scene.getCode() == code.intValue()){
                return scene;
            }
        }
        return null;
    }
}
