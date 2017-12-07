package android.rezkyauliapratama.id.robusta.database.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Rezky Aulia Pratama on 12/7/2017.
 */
@Entity(nameInDb = "RawanBencanaTbl",indexes = {})
public class RawanBencanaTbl implements Parcelable{

    @Id(autoincrement = true)
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("bencana")
    @Expose
    private String bencana;
    @SerializedName("lokasi")
    @Expose
    private String lokasi;
    @SerializedName("kecamatan")
    @Expose
    private String kecamatan;
    @SerializedName("kota")
    @Expose
    private String kota;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @Generated(hash = 748660298)
    public RawanBencanaTbl(Long id, String bencana, String lokasi, String kecamatan,
            String kota, Double latitude, Double longitude) {
        this.id = id;
        this.bencana = bencana;
        this.lokasi = lokasi;
        this.kecamatan = kecamatan;
        this.kota = kota;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    @Generated(hash = 894499667)
    public RawanBencanaTbl() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBencana() {
        return this.bencana;
    }
    public void setBencana(String bencana) {
        this.bencana = bencana;
    }
    public String getLokasi() {
        return this.lokasi;
    }
    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
    public String getKecamatan() {
        return this.kecamatan;
    }
    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }
    public String getKota() {
        return this.kota;
    }
    public void setKota(String kota) {
        this.kota = kota;
    }
    public Double getLatitude() {
        return this.latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return this.longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.bencana);
        dest.writeString(this.lokasi);
        dest.writeString(this.kecamatan);
        dest.writeString(this.kota);
        dest.writeValue(this.latitude);
        dest.writeValue(this.longitude);
    }

    protected RawanBencanaTbl(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.bencana = in.readString();
        this.lokasi = in.readString();
        this.kecamatan = in.readString();
        this.kota = in.readString();
        this.latitude = (Double) in.readValue(Double.class.getClassLoader());
        this.longitude = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<RawanBencanaTbl> CREATOR = new Creator<RawanBencanaTbl>() {
        @Override
        public RawanBencanaTbl createFromParcel(Parcel source) {
            return new RawanBencanaTbl(source);
        }

        @Override
        public RawanBencanaTbl[] newArray(int size) {
            return new RawanBencanaTbl[size];
        }
    };
}
