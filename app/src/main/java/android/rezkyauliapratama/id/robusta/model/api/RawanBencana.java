
package android.rezkyauliapratama.id.robusta.model.api;

import android.rezkyauliapratama.id.robusta.model.api.Location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RawanBencana {

    @SerializedName("id")
    @Expose
    private Integer id;
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
    @SerializedName("location")
    @Expose
    private Location location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBencana() {
        return bencana;
    }

    public void setBencana(String bencana) {
        this.bencana = bencana;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}