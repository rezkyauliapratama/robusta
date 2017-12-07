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
@Entity(nameInDb = "PuskesmasTbl",indexes = {})
public class PuskesmasTbl {

    @Id(autoincrement = true)
    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("nama_Puskesmas")
    @Expose
    private String namaPuskesmas;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("telepon")
    @Expose
    private String telepon = null;
    @SerializedName("faximile")
    @Expose
    private String faximile = null;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("kepala_puskesmas")
    @Expose
    private String kepalaPuskesmas;
    @SerializedName("kode_kota")
    @Expose
    private String kodeKota;
    @SerializedName("kode_kecamatan")
    @Expose
    private String kodeKecamatan;
    @SerializedName("kode_kelurahan")
    @Expose
    private String kodeKelurahan;
    @Generated(hash = 2049428571)
    public PuskesmasTbl(Long id, String namaPuskesmas, Double latitude,
            Double longitude, String telepon, String faximile, String email,
            String kepalaPuskesmas, String kodeKota, String kodeKecamatan,
            String kodeKelurahan) {
        this.id = id;
        this.namaPuskesmas = namaPuskesmas;
        this.latitude = latitude;
        this.longitude = longitude;
        this.telepon = telepon;
        this.faximile = faximile;
        this.email = email;
        this.kepalaPuskesmas = kepalaPuskesmas;
        this.kodeKota = kodeKota;
        this.kodeKecamatan = kodeKecamatan;
        this.kodeKelurahan = kodeKelurahan;
    }
    @Generated(hash = 750523726)
    public PuskesmasTbl() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNamaPuskesmas() {
        return this.namaPuskesmas;
    }
    public void setNamaPuskesmas(String namaPuskesmas) {
        this.namaPuskesmas = namaPuskesmas;
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
    public String getTelepon() {
        return this.telepon;
    }
    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
    public String getFaximile() {
        return this.faximile;
    }
    public void setFaximile(String faximile) {
        this.faximile = faximile;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getKepalaPuskesmas() {
        return this.kepalaPuskesmas;
    }
    public void setKepalaPuskesmas(String kepalaPuskesmas) {
        this.kepalaPuskesmas = kepalaPuskesmas;
    }
    public String getKodeKota() {
        return this.kodeKota;
    }
    public void setKodeKota(String kodeKota) {
        this.kodeKota = kodeKota;
    }
    public String getKodeKecamatan() {
        return this.kodeKecamatan;
    }
    public void setKodeKecamatan(String kodeKecamatan) {
        this.kodeKecamatan = kodeKecamatan;
    }
    public String getKodeKelurahan() {
        return this.kodeKelurahan;
    }
    public void setKodeKelurahan(String kodeKelurahan) {
        this.kodeKelurahan = kodeKelurahan;
    }

   }
