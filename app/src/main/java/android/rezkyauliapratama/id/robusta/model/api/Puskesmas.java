
package android.rezkyauliapratama.id.robusta.model.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Puskesmas {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama_Puskesmas")
    @Expose
    private String namaPuskesmas;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("telepon")
    @Expose
    private List<String> telepon = null;
    @SerializedName("faximile")
    @Expose
    private List<String> faximile = null;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaPuskesmas() {
        return namaPuskesmas;
    }

    public void setNamaPuskesmas(String namaPuskesmas) {
        this.namaPuskesmas = namaPuskesmas;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKepalaPuskesmas() {
        return kepalaPuskesmas;
    }

    public void setKepalaPuskesmas(String kepalaPuskesmas) {
        this.kepalaPuskesmas = kepalaPuskesmas;
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
}
