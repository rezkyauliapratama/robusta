package android.rezkyauliapratama.id.robusta.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Rezky Aulia Pratama on 9/7/2017.
 */

public class place implements Parcelable {
    public String name;
    public int type;
    public float rate;
    public String attribute;
    public String phone;
    public LatLng latLng;


    public place(String name, int type, float rate, String attribute, String phone, LatLng latLng) {
        this.name = name;
        this.type = type;
        this.rate = rate;
        this.attribute = attribute;
        this.phone = phone;
        this.latLng = latLng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.type);
        dest.writeFloat(this.rate);
        dest.writeString(this.attribute);
        dest.writeString(this.phone);
        dest.writeParcelable(this.latLng, flags);
    }

    public place() {
    }

    protected place(Parcel in) {
        this.name = in.readString();
        this.type = in.readInt();
        this.rate = in.readFloat();
        this.attribute = in.readString();
        this.phone = in.readString();
        this.latLng = in.readParcelable(LatLng.class.getClassLoader());
    }

    public static final Creator<place> CREATOR = new Creator<place>() {
        @Override
        public place createFromParcel(Parcel source) {
            return new place(source);
        }

        @Override
        public place[] newArray(int size) {
            return new place[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public static Creator<place> getCREATOR() {
        return CREATOR;
    }
}
