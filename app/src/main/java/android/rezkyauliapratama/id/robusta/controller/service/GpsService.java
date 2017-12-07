package android.rezkyauliapratama.id.robusta.controller.service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.rezkyauliapratama.id.robusta.model.GpsEvent;
import android.rezkyauliapratama.id.robusta.observer.RxBus;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.google.gson.Gson;

import timber.log.Timber;

/**
 * Created by Rezky Aulia Pratama on 5/31/2017.
 */

public class GpsService extends Service implements LocationListener {
    Context context;
    int startId;
    GpsEvent event;
    private LocationManager locationManager;
    private Location finalLocation;
    Intent intent;
//    static OnLocationEventListener listener;

    // Flag for GPS status
    boolean isGPSEnabled = false;
    //flag for permission
    boolean isPermissionAllow = false;
    // Flag for network status
    boolean isNetworkEnabled = false;

    private static final String TAG = "MyService";
    private boolean isRunning = false;
    private Looper looper;
    private ServiceHandler serviceHandler;

    private final long MIN_DISTANCE = 10;
    private final long MIN_TIME = 6000;

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            Timber.e("handle message");
            triggerLocation(looper);
            startId = msg.arg1;
//            stopSelf(msg.arg1);

        }


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        startId = 0;
        HandlerThread handlerthread = new HandlerThread("GpsService", Process.
                THREAD_PRIORITY_FOREGROUND);
        handlerthread.start();
        looper = handlerthread.getLooper();
        serviceHandler = new ServiceHandler(looper);
        isRunning = true;
        Timber.e("OnCreate service");
        context = getBaseContext();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Message msg = serviceHandler.obtainMessage();
        msg.arg1 = startId;
        serviceHandler.sendMessage(msg);
        //If service is killed while starting, it restarts.
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        stopSelfResult(startId);
        isRunning = false;
        Timber.e("Services Destroy");
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }


    }

    public void triggerLocation(Looper looper){
        Timber.e("Triggerlocation");

//        if (context==null)
//            Timber.e("context == null");

        try{

            locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

            if (locationManager != null){
//                Timber.e("Trigger Location location manager != null");

                checkStatus();
                if (isGPSEnabled) {
//                    Timber.e("isGPSEnabled");
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                        Timber.e("permission denied");
                        return;
                    }

                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DISTANCE, this,looper);
                    if (locationManager != null){
//                        Timber.e("Location manager != null");
                        finalLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                        Timber.e("LOCATION : "+new Gson().toJson(finalLocation));
                    }
                }

                if (finalLocation == null) {
                    if (isNetworkEnabled) {
//                                Timber.e("NETWORK_PROVIDER");
                        locationManager.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER,
                                MIN_TIME,
                                MIN_DISTANCE, this,looper);
                        if (locationManager != null) {
//                                    Timber.e("locationmanager != null");

                            finalLocation = locationManager
                                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                        }
                    }
                }

                sendEvent();
            }else{
//                Timber.e("Location manager == null");
            }

        } catch (Exception e) {
            Timber.e("triggerlocation error : "+e.getMessage());
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        finalLocation = location;
        sendEvent();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
            triggerLocation(this.looper);
    }

    @Override
    public void onProviderEnabled(String provider) {
        triggerLocation(this.looper);
        sendEvent();
    }

    @Override
    public void onProviderDisabled(String provider) {
        sendEvent();
    }

    private void checkStatus(){
        // Getting GPS status
        isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        // Getting network status
        isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);


        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isPermissionAllow = false;
        }else{
            isPermissionAllow = true;
        }
    }


    private void sendEvent(){
        checkStatus();
        final GpsEvent event = new GpsEvent();
        event.setInternetEnable(isNetworkEnabled);
        event.setPermissionAllow(isPermissionAllow);
        event.setGpsOn(isGPSEnabled);
        if (finalLocation!=null){
            event.setLatitude(finalLocation.getLatitude());
            event.setLongitude(finalLocation.getLongitude());
        }

        if (event.getLongitude() == 0 && event.getLatitude() == 0){
            GPSTracker gpsTracker = new GPSTracker(getBaseContext(), new GPSTracker.OnLocationEventListener() {
                @Override
                public void onChange(GPSTracker gpsTracker, Location location) {
                   /* gpsTracker.getLocation();
                    event.setLatitude(gpsTracker.getLatitude());
                    event.setLongitude(gpsTracker.getLongitude());*/
                }
            });
            gpsTracker.getLocation();
            event.setLatitude(gpsTracker.getLatitude());
            event.setLongitude(gpsTracker.getLongitude());
        }

        Timber.e("GPS SERVICE : "+new Gson().toJson(event));

        RxBus.getInstance().post(event);
    }


    public static class OnEvent {
    }
}
