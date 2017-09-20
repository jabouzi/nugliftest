package com.skanderjabouzi.nuglifandroidtest.model;

public class MyLocation {
    private double latitude;
    private double longitude;
    private double time;

    public MyLocation() {
        latitude = 0;
        longitude = 0;
        time = 0;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getTime() {
        return time;
    }
}
