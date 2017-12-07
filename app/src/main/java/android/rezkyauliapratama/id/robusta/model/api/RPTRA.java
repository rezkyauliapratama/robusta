package android.rezkyauliapratama.id.robusta.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RPTRA {

    @SerializedName("id")
    @Expose
    private Integer id;
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
    @SerializedName("location")
    @Expose
    private Location location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaRptra() {
        return namaRptra;
    }

    public void setNamaRptra(String namaRptra) {
        this.namaRptra = namaRptra;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getLuas() {
        return luas;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }

    public String getWaktuPeresmian() {
        return waktuPeresmian;
    }

    public void setWaktuPeresmian(String waktuPeresmian) {
        this.waktuPeresmian = waktuPeresmian;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public String getPermasalahan() {
        return permasalahan;
    }

    public void setPermasalahan(String permasalahan) {
        this.permasalahan = permasalahan;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}