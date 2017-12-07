
package android.rezkyauliapratama.id.robusta.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "KursusTbl",indexes = {})
public class KursusTbl {

    @Id(autoincrement = true)
    private Long id;

    @SerializedName("nama_lembaga")
    @Expose
    private String namaLembaga;
    @SerializedName("jenis kursus")
    @Expose
    private String jenisKursus;
    @SerializedName("kota")
    @Expose
    private String kota;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("telp_number")
    @Expose
    private String telpNumber;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("tanggal_berdiri")
    @Expose
    private String tanggalBerdiri;
    @SerializedName("nama_pimpinan")
    @Expose
    private String namaPimpinan;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;

    @Generated(hash = 670028277)
    public KursusTbl(Long id, String namaLembaga, String jenisKursus, String kota,
            String alamat, String telpNumber, String website, String email,
            String tanggalBerdiri, String namaPimpinan, Double latitude,
            Double longitude) {
        this.id = id;
        this.namaLembaga = namaLembaga;
        this.jenisKursus = jenisKursus;
        this.kota = kota;
        this.alamat = alamat;
        this.telpNumber = telpNumber;
        this.website = website;
        this.email = email;
        this.tanggalBerdiri = tanggalBerdiri;
        this.namaPimpinan = namaPimpinan;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Generated(hash = 339867061)
    public KursusTbl() {
    }

    public String getNamaLembaga() {
        return namaLembaga;
    }

    public void setNamaLembaga(String namaLembaga) {
        this.namaLembaga = namaLembaga;
    }

    public String getJenisKursus() {
        return jenisKursus;
    }

    public void setJenisKursus(String jenisKursus) {
        this.jenisKursus = jenisKursus;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelpNumber() {
        return telpNumber;
    }

    public void setTelpNumber(String telpNumber) {
        this.telpNumber = telpNumber;
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

    public String getTanggalBerdiri() {
        return tanggalBerdiri;
    }

    public void setTanggalBerdiri(String tanggalBerdiri) {
        this.tanggalBerdiri = tanggalBerdiri;
    }

    public String getNamaPimpinan() {
        return namaPimpinan;
    }

    public void setNamaPimpinan(String namaPimpinan) {
        this.namaPimpinan = namaPimpinan;
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
