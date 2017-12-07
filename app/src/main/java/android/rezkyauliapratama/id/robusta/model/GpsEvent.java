package android.rezkyauliapratama.id.robusta.model;

/**
 * Created by Rezky Aulia Pratama on 5/31/2017.
 */

public class GpsEvent {
    double latitude;
    double longitude;
    boolean isPermissionAllow;
    boolean isGpsOn;
    boolean isInternetEnable;
    boolean isMockLocation;

    public GpsEvent(){
        isPermissionAllow = true;
        isGpsOn = true;
        isInternetEnable = true;
        isMockLocation = false;
        latitude =0.0;
        longitude =0.0;
    }

    public boolean isInternetEnable() {
        return isInternetEnable;
    }

    public void setInternetEnable(boolean internetEnable) {
        isInternetEnable = internetEnable;
    }

    public boolean isMockLocation() {
        return isMockLocation;
    }

    public void setMockLocation(boolean mockLocation) {
        isMockLocation = mockLocation;
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

    public boolean isPermissionAllow() {
        return isPermissionAllow;
    }

    public void setPermissionAllow(boolean permissionAllow) {
        isPermissionAllow = permissionAllow;
    }

    public boolean isGpsOn() {
        return isGpsOn;
    }

    public void setGpsOn(boolean gpsOn) {
        isGpsOn = gpsOn;
    }
}
