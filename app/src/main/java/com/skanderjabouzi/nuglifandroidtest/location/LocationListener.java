package com.skanderjabouzi.nuglifandroidtest.location;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationListener implements android.location.LocationListener {
    private float latitude  = 0.0f;
    private float longitude = 0.0f;
    private boolean gotLocation = false;
    private LocationManager locationManager;

    public LocationListener(LocationManager _locationManager) {
        this.locationManager = _locationManager;
    }

    public void onLocationChanged(Location location) {
        latitude = (float) location.getLatitude();
        longitude = (float) location.getLongitude();
        locationManager.removeUpdates(this);
        gotLocation = true;
    }

    public float getLat(){
        return latitude;
    }

    public float getLong(){
        return longitude;
    }

    public Boolean gotLocation(){
        return gotLocation;
    }

    public void onProviderDisabled(String provider) {}

    public void onProviderEnabled(String provider) {}

    public void onStatusChanged(String provider, int status, Bundle extras) {}
}
