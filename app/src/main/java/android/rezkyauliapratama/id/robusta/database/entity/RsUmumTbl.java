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
@Entity(nameInDb = "RsUmumTbl",indexes = {})
public class RsUmumTbl{
    @Id(autoincrement = true)
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("nama_rsu")
    @Expose
    private String namaRsu;
    @SerializedName("jenis_rsu")
    @Expose
    private String jenisRsu;
    @SerializedName("kode_pos")
    @Expose
    private String kodePos;
    @SerializedName("telepon")
    @Expose
    private String telepon = null;
    @SerializedName("faximile")
    @Expose
    private String faximile = null;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("kode_kota")
    @Expose
    private String kodeKota;
    @SerializedName("kode_kecamatan")
    @Expose
    private String kodeKecamatan;
    @SerializedName("kode_kelurahan")
    @Expose
    private String kodeKelurahan;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @Generated(hash = 669519731)
    public RsUmumTbl(Long id, String namaRsu, String jenisRsu, String kodePos,
            String telepon, String faximile, String website, String email,
            String kodeKota, String kodeKecamatan, String kodeKelurahan,
            Double latitude, Double longitude) {
        this.id = id;
        this.namaRsu = namaRsu;
        this.jenisRsu = jenisRsu;
        this.kodePos = kodePos;
        this.telepon = telepon;
        this.faximile = faximile;
        this.website = website;
        this.email = email;
        this.kodeKota = kodeKota;
        this.kodeKecamatan = kodeKecamatan;
        this.kodeKelurahan = kodeKelurahan;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    @Generated(hash = 1580995023)
    public RsUmumTbl() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNamaRsu() {
        return this.namaRsu;
    }
    public void setNamaRsu(String namaRsu) {
        this.namaRsu = namaRsu;
    }
    public String getJenisRsu() {
        return this.jenisRsu;
    }
    public void setJenisRsu(String jenisRsu) {
        this.jenisRsu = jenisRsu;
    }
    public String getKodePos() {
        return this.kodePos;
    }
    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
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
    public String getWebsite() {
        return this.website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
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

}
