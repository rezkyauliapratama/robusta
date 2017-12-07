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

@Entity(nameInDb = "RptraTbl",indexes = {})
public class RptraTbl implements Parcelable{

    @Id(autoincrement = true)
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("nama_rptra")
    @Expose
    private String namaRptra;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("kelurahan")
    @Expose
    private String kelurahan;
    @SerializedName("kecamatan")
    @Expose
    private String kecamatan;
    @SerializedName("luas")
    @Expose
    private String luas;
    @SerializedName("waktu_peresmian")
    @Expose
    private String waktuPeresmian;
    @SerializedName("telepon")
    @Expose
    private String telepon;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("fasilitas")
    @Expose
    private String fasilitas;
    @SerializedName("permasalahan")
    @Expose
    private String permasalahan;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @Generated(hash = 1531068171)
    public RptraTbl(Long id, String namaRptra, String alamat, String kelurahan,
            String kecamatan, String luas, String waktuPeresmian, String telepon,
            String email, String fasilitas, String permasalahan, Double latitude,
            Double longitude) {
        this.id = id;
        this.namaRptra = namaRptra;
        this.alamat = alamat;
        this.kelurahan = kelurahan;
        this.kecamatan = kecamatan;
        this.luas = luas;
        this.waktuPeresmian = waktuPeresmian;
        this.telepon = telepon;
        this.email = email;
        this.fasilitas = fasilitas;
        this.permasalahan = permasalahan;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    @Generated(hash = 359354205)
    public RptraTbl() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNamaRptra() {
        return this.namaRptra;
    }
    public void setNamaRptra(String namaRptra) {
        this.namaRptra = namaRptra;
    }
    public String getAlamat() {
        return this.alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public String getKelurahan() {
        return this.kelurahan;
    }
    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }
    public String getKecamatan() {
        return this.kecamatan;
    }
    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }
    public String getLuas() {
        return this.luas;
    }
    public void setLuas(String luas) {
        this.luas = luas;
    }
    public String getWaktuPeresmian() {
        return this.waktuPeresmian;
    }
    public void setWaktuPeresmian(String waktuPeresmian) {
        this.waktuPeresmian = waktuPeresmian;
    }
    public String getTelepon() {
        return this.telepon;
    }
    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFasilitas() {
        return this.fasilitas;
    }
    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }
    public String getPermasalahan() {
        return this.permasalahan;
    }
    public void setPermasalahan(String permasalahan) {
        this.permasalahan = permasalahan;
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
        dest.writeString(this.namaRptra);
        dest.writeString(this.alamat);
        dest.writeString(this.kelurahan);
        dest.writeString(this.kecamatan);
        dest.writeString(this.luas);
        dest.writeString(this.waktuPeresmian);
        dest.writeString(this.telepon);
        dest.writeString(this.email);
        dest.writeString(this.fasilitas);
        dest.writeString(this.permasalahan);
        dest.writeValue(this.latitude);
        dest.writeValue(this.longitude);
    }

    protected RptraTbl(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.namaRptra = in.readString();
        this.alamat = in.readString();
        this.kelurahan = in.readString();
        this.kecamatan = in.readString();
        this.luas = in.readString();
        this.waktuPeresmian = in.readString();
        this.telepon = in.readString();
        this.email = in.readString();
        this.fasilitas = in.readString();
        this.permasalahan = in.readString();
        this.latitude = (Double) in.readValue(Double.class.getClassLoader());
        this.longitude = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<RptraTbl> CREATOR = new Creator<RptraTbl>() {
        @Override
        public RptraTbl createFromParcel(Parcel source) {
            return new RptraTbl(source);
        }

        @Override
        public RptraTbl[] newArray(int size) {
            return new RptraTbl[size];
        }
    };
}
