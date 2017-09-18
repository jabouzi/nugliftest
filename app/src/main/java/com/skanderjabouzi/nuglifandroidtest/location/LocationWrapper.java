package com.skanderjabouzi.nuglifandroidtest.location;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class LocationWrapper {
    private LocationManager locationManager;
    private LocationListener locationListener;
    public static final String CONN_INTENT = "com.skanderjabouzi.nuglifandroidtest.CONN_INTENT";


    public LocationWrapper(Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener(locationManager);
        List<String> listPermissionsNeeded = new ArrayList<>();

        int loc = ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION);
        int loc2 = ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION);

        if (loc2 != PackageManager.PERMISSION_GRANTED) {
            Log.e("PRESENTER", "loc2 NO");
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (loc != PackageManager.PERMISSION_GRANTED) {
            Log.e("PRESENTER", "loc NO");
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        if (!listPermissionsNeeded.isEmpty())
        {
            Intent intent;
            intent = new Intent();
            intent.setAction(CONN_INTENT);
            intent.putExtra("CONNSTATE", "false");
            context.sendBroadcast(intent);
            Log.e("PRESENTER", "LocationWrapper NO");
        } else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    public void killLocationServices(){
        locationManager.removeUpdates(locationListener);
    }

    public float getLat(){
        return locationListener.getLat();
    }

    public float getLong(){
        return locationListener.getLong();
    }

    public Boolean gotLocation(){
        return locationListener.gotLocation();
    }
}
