package android.rezkyauliapratama.id.robusta.controller.service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;


public class GPSTracker extends Service implements LocationListener {
    private static final String TAG = GPSTracker.class.getSimpleName();
    private final Context mContext;
    private final OnLocationEventListener listener;

    // Flag for GPS status
    boolean isGPSEnabled = false;

    // Flag for network status
    boolean isNetworkEnabled = false;

    // Flag for GPS status
    boolean canGetLocation = false;

    Location location; // Location
    double latitude; // Latitude
    double longitude; // Longitude

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 6000; // 1 minute

    // Declaring a Location Manager
    protected LocationManager locationManager;
    private int gps_flag;

    public GPSTracker(Context context, OnLocationEventListener listener) {
        this.mContext = context;
        this.listener = listener;
        getLocation();
    }



    public Location getLocation() {
        try {
            locationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);
//            try {
//                Log.d(TAG ,"Removing Test providers");
//                locationManager.removeTestProvider(LocationManager.GPS_PROVIDER);
//            } catch (IllegalArgumentException error) {
//                Log.d(TAG,"Got exception in removing test  provider");
//            }
            // Getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // Getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // No network provider is enabled
                gps_flag = 0;
            } else {
                this.canGetLocation = true;

                // If GPS enabled, get latitude/longitude using GPS Services
                if (isGPSEnabled) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (
                            ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                                    || ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                            ) {
                        Log.d(TAG, "Permission Denied");
                        return null;
                    }

                    Log.d(TAG, "GPS_PROVIDER");
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        Log.d(TAG, "Get Last known Location");
                        if (location != null) {
                            //System.out.println("gps_flag = GPS Enabled");
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            gps_flag = 1;
                        }
                    }

                } else {
                    if (location == null) {
                        if (isNetworkEnabled) {
                            Log.d(TAG, "NETWORK_PROVIDER");
                            locationManager.requestLocationUpdates(
                                    LocationManager.NETWORK_PROVIDER,
                                    MIN_TIME_BW_UPDATES,
                                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                            if (locationManager != null) {
                                location = locationManager
                                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                                if (location != null) {
                                    //System.out.println("gps_flag = GPS Network");
                                    latitude = location.getLatitude();
                                    longitude = location.getLongitude();
                                    gps_flag = 2;
                                }
                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }


    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app.
     */
    public void stopUsingGPS() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                ) {

            return;
        }
        if (locationManager != null) {
            locationManager.removeUpdates(GPSTracker.this);
        }
    }


    /**
     * Function to get latitude
     */
    public double getLatitude() {
        if (location != null) {
            latitude = location.getLatitude();
        }

        // return latitude
        return latitude;
    }


    /**
     * Function to get longitude
     */
    public double getLongitude() {
        if (location != null) {
            longitude = location.getLongitude();
        }

        // return longitude
        return longitude;
    }

    /**
     * Function to check GPS/Wi-Fi enabled
     *
     * @return boolean
     */
    public boolean canGetLocation() {
        return this.canGetLocation;
    }


    /**
     * Function to show settings alert dialog.
     * On pressing the Settings button it will launch Settings Options.
     */
    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // On pressing the Settings button.
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });

        // On pressing the cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    public boolean isMockLocation() {
        Log.d(TAG, "isMockLocation");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2 && location != null) {
            Log.d(TAG, "Mock Location 1 : "+location.isFromMockProvider());
            return location.isFromMockProvider()
                    || !Settings.Secure.getString(mContext.getContentResolver(),
                    Settings.Secure.ALLOW_MOCK_LOCATION).equals("0");
        } else {
            Log.d(TAG, "Mock Location 2");
            return !Settings.Secure.getString(mContext.getContentResolver(),
                    Settings.Secure.ALLOW_MOCK_LOCATION).equals("0");
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged");

        if (location != null)
            this.location = location;

        listener.onChange(this,location);
    }


    @Override
    public void onProviderDisabled(String provider) {
        listener.onChange(this, location);
    }


    @Override
    public void onProviderEnabled(String provider) {
        listener.onChange(this, location);
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }


    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    public interface OnLocationEventListener {
        void onChange(GPSTracker gpsTracker, Location location);
    }

    public static class OnEvent {
    }
}
