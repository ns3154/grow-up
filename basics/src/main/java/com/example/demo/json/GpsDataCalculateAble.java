package com.example.demo.json;

public interface GpsDataCalculateAble {

    boolean isMoving();

    String getCarId();

    double getLongitude();

    double getLatitude();

    void setLongitude(double lng);

    void setLatitude(double lat);

    int getInRail();

    void setInRail(int state);

    double getLatitudeFilter();

    double getLongitudeFilter();

    int getIncludedMileage();

    int getLocationType();

    Integer getDefend();

    Integer getAcc();

    void setLongitudeFilter(double longitude);

    void setLatitudeFilter(double latitude);

    void setDistance(String calculateDistance);

    double getVoltage();

    double getHdop();

    int getSatellite();


    void setIncludedMileage(int i);

    Double getA5sBeforeMileage();

}
