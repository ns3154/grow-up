package com.example.demo.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class BaseEntity {
    private String cmd;

    //消息序列号 hex
    private String seri;

    private String carId;

    public BaseEntity(String carId) {
        this.carId = carId;
    }

    protected JSONObject data;

    public JSONObject toMap() {
        JSONObject result = ((JSONObject) JSONObject.toJSON(this));
        return result;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getSeri() {
        return seri;
    }

    public void setSeri(String seri) {
        this.seri = seri;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }
}
