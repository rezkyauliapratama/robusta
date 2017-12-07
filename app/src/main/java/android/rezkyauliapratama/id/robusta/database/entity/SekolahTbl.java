
package android.rezkyauliapratama.id.robusta.database.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "SekolahTbl",indexes = {})
public class SekolahTbl{

    @Id(autoincrement = true)
    private Long id;

    @SerializedName("npsn")
    @Expose
    private String npsn;
    @SerializedName("nama_sekolah")
    @Expose
    private String namaSekolah;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("kelurahan")
    @Expose
    private String kelurahan;
    @SerializedName("kecamatan")
    @Expose
    private String kecamatan;
    @SerializedName("jumlah_siswa")
    @Expose
    private String jumlahSiswa;
    @SerializedName("jumlah_guru")
    @Expose
    private String jumlahGuru;
    @SerializedName("kepala_sekolah")
    @Expose
    private String kepalaSekolah;
    @SerializedName("telp_sekolah")
    @Expose
    private String telpSekolah;
    @SerializedName("akreditas")
    @Expose
    private String akreditas;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("tingkat")
    @Expose
    private String tingkat;
    @Generated(hash = 1096832373)
    public SekolahTbl(Long id, String npsn, String namaSekolah, String alamat,
            String kelurahan, String kecamatan, String jumlahSiswa,
            String jumlahGuru, String kepalaSekolah, String telpSekolah,
            String akreditas, Double latitude, Double longitude, String tingkat) {
        this.id = id;
        this.npsn = npsn;
        this.namaSekolah = namaSekolah;
        this.alamat = alamat;
        this.kelurahan = kelurahan;
        this.kecamatan = kecamatan;
        this.jumlahSiswa = jumlahSiswa;
        this.jumlahGuru = jumlahGuru;
        this.kepalaSekolah = kepalaSekolah;
        this.telpSekolah = telpSekolah;
        this.akreditas = akreditas;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tingkat = tingkat;
    }
    @Generated(hash = 1485862406)
    public SekolahTbl() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNpsn() {
        return this.npsn;
    }
    public void setNpsn(String npsn) {
        this.npsn = npsn;
    }
    public String getNamaSekolah() {
        return this.namaSekolah;
    }
    public void setNamaSekolah(String namaSekolah) {
        this.namaSekolah = namaSekolah;
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
    public String getJumlahSiswa() {
        return this.jumlahSiswa;
    }
    public void setJumlahSiswa(String jumlahSiswa) {
        this.jumlahSiswa = jumlahSiswa;
    }
    public String getJumlahGuru() {
        return this.jumlahGuru;
    }
    public void setJumlahGuru(String jumlahGuru) {
        this.jumlahGuru = jumlahGuru;
    }
    public String getKepalaSekolah() {
        return this.kepalaSekolah;
    }
    public void setKepalaSekolah(String kepalaSekolah) {
        this.kepalaSekolah = kepalaSekolah;
    }
    public String getTelpSekolah() {
        return this.telpSekolah;
    }
    public void setTelpSekolah(String telpSekolah) {
        this.telpSekolah = telpSekolah;
    }
    public String getAkreditas() {
        return this.akreditas;
    }
    public void setAkreditas(String akreditas) {
        this.akreditas = akreditas;
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
    public String getTingkat() {
        return this.tingkat;
    }
    public void setTingkat(String tingkat) {
        this.tingkat = tingkat;
    }
    
    }
