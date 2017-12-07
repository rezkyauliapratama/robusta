package android.rezkyauliapratama.id.robusta.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rezky Aulia Pratama on 12/7/2017.
 */

public class RsUmum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nama_rsu")
    @Expose
    private String namaRsu;
    @SerializedName("jenis_rsu")
    @Expose
    private String jenisRsu;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("kode_pos")
    @Expose
    private String kodePos;
    @SerializedName("telepon")
    @Expose
    private List<String> telepon = null;
    @SerializedName("faximile")
    @Expose
    private List<String> faximile = null;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaRsu() {
        return namaRsu;
    }

    public void setNamaRsu(String namaRsu) {
        this.namaRsu = namaRsu;
    }

    public String getJenisRsu() {
        return jenisRsu;
    }

    public void setJenisRsu(String jenisRsu) {
        this.jenisRsu = jenisRsu;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    public List<String> getTelepon() {
        return telepon;
    }

    public void setTelepon(List<String> telepon) {
        this.telepon = telepon;
    }

    public List<String> getFaximile() {
        return faximile;
    }

    public void setFaximile(List<String> faximile) {
        this.faximile = faximile;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKodeKota() {
        return kodeKota;
    }

    public void setKodeKota(String kodeKota) {
        this.kodeKota = kodeKota;
    }

    public String getKodeKecamatan() {
        return kodeKecamatan;
    }

    public void setKodeKecamatan(String kodeKecamatan) {
        this.kodeKecamatan = kodeKecamatan;
    }

    public String getKodeKelurahan() {
        return kodeKelurahan;
    }

    public void setKodeKelurahan(String kodeKelurahan) {
        this.kodeKelurahan = kodeKelurahan;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}
