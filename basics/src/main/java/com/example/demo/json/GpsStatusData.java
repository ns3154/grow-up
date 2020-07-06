package com.example.demo.json;


/**
 * project  concox
 *
 * @author 鸟叔
 * @description
 * @date 2019-08-06 16:12
 * @since v1.0
 * <p>
 * Copyright ©  2019-08-06~  All rights reserved.
 */
public class GpsStatusData extends BaseEntity implements GpsDataCalculateAble {
    /**
     * 数据采集时间
     */
    private long gpsTime;
    /**
     * 卫星个数
     */
    private int satellite;
    /**
     * 经度
     */
    private double longitude;
    /**
     * 纬度
     */
    private double latitude;
    /**
     * 速度
     */
    private double speed;
    /**
     * 方向
     */
    private double course;

    private double voltage;

    private String dumpEle;//char 设备剩余电量

    // 最近消费电量
    private String consumePower;
    /**
     * 国家代号
     */
    private int mcc;
    /**
     * 移动网号码
     */
    private int mnc;
    /**
     * 位置区码
     */
    private int lac;
    /**
     * 基站编号
     */
    private int cellId;
    /**
     * 电门状态
     */
    private int acc;
    /**
     * 数据上传模式
     */
    private int dataModel;
    /**
     * 是否为补点上传 1 补 0 正常
     */
    private int dataFix;
    /**
     * 里程 大部分没有这个
     */
    private String distance;

    public GpsStatusData(String carId) {
        super(carId);
    }

    public long getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(long time) {
        this.gpsTime = time;
    }

    @Override
    public boolean isMoving() {
        //康凯斯家的设备GPS静止不上报
        return true;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public int getInRail() {
        return 0;
    }

    @Override
    public void setInRail(int state) {

    }

    @Override
    public double getLatitudeFilter() {
        return latitude;
    }

    @Override
    public double getLongitudeFilter() {
        return longitude;
    }

    @Override
    public int getIncludedMileage() {
        return 1;
    }

    @Override
    public int getLocationType() {
        return 1;
    }

    @Override
    public Integer getDefend() {
        return 1;
    }

    @Override
    public Integer getAcc() {
        return 1;
    }

    public double getCourse() {
        return course;
    }

    public void setCourse(double course) {
        this.course = course;
    }

    public int getMcc() {
        return mcc;
    }

    public void setMcc(int mcc) {
        this.mcc = mcc;
    }

    public int getMnc() {
        return mnc;
    }

    public void setMnc(int mnc) {
        this.mnc = mnc;
    }

    public int getLac() {
        return lac;
    }

    public void setLac(int lac) {
        this.lac = lac;
    }

    public int getCellId() {
        return cellId;
    }

    public void setCellId(int cellId) {
        this.cellId = cellId;
    }


    @Override
    public void setLongitudeFilter(double longitude) {

    }

    @Override
    public void setLatitudeFilter(double latitude) {

    }

    @Override
    public void setDistance(String calculateDistance) {
        this.distance = calculateDistance;
    }

    @Override
    public double getVoltage() {
        return voltage;
    }

    @Override
    public double getHdop() {
        return 0;
    }

    @Override
    public int getSatellite() {
        return satellite;
    }

    @Override
    public void setIncludedMileage(int i) {

    }

    @Override
    public Double getA5sBeforeMileage() {
        return 0.0;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }

    public int getDataModel() {
        return dataModel;
    }

    public void setDataModel(int dataModel) {
        this.dataModel = dataModel;
    }

    public int getDataFix() {
        return dataFix;
    }

    public void setDataFix(int dataFix) {
        this.dataFix = dataFix;
    }

    public String getDistance() {
        return distance;
    }

    public String getDumpEle() {
        return dumpEle;
    }

    public void setDumpEle(String dumpEle) {
        this.dumpEle = dumpEle;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public String getConsumePower() {
        return consumePower;
    }

    public void setConsumePower(String consumePower) {
        this.consumePower = consumePower;
    }

    public void setSatellite(int satelliteCount) {
        this.satellite = satelliteCount;
    }

}
