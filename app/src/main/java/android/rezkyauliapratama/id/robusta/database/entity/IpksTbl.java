
package android.rezkyauliapratama.id.robusta.database.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "IpksTbl",indexes = {})
public class IpksTbl implements Parcelable{

    @Id(autoincrement = true)
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("kelurahan")
    @Expose
    private String kelurahan;
    @SerializedName("ipks")
    @Expose
    private Double ipks;
    @SerializedName("kategori")
    @Expose
    private String kategori;
    @SerializedName("kecamatan")
    @Expose
    private String kecamatan;
    @SerializedName("kota")
    @Expose
    private String kota;
    @Generated(hash = 1469972607)
    public IpksTbl(Long id, String kelurahan, Double ipks, String kategori,
            String kecamatan, String kota) {
        this.id = id;
        this.kelurahan = kelurahan;
        this.ipks = ipks;
        this.kategori = kategori;
        this.kecamatan = kecamatan;
        this.kota = kota;
    }
    @Generated(hash = 1172936183)
    public IpksTbl() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getKelurahan() {
        return this.kelurahan;
    }
    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }
    public Double getIpks() {
        return this.ipks;
    }
    public void setIpks(Double ipks) {
        this.ipks = ipks;
    }
    public String getKategori() {
        return this.kategori;
    }
    public void setKategori(String kategori) {
        this.kategori = kategori;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.kelurahan);
        dest.writeValue(this.ipks);
        dest.writeString(this.kategori);
        dest.writeString(this.kecamatan);
        dest.writeString(this.kota);
    }

    protected IpksTbl(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.kelurahan = in.readString();
        this.ipks = (Double) in.readValue(Double.class.getClassLoader());
        this.kategori = in.readString();
        this.kecamatan = in.readString();
        this.kota = in.readString();
    }

    public static final Creator<IpksTbl> CREATOR = new Creator<IpksTbl>() {
        @Override
        public IpksTbl createFromParcel(Parcel source) {
            return new IpksTbl(source);
        }

        @Override
        public IpksTbl[] newArray(int size) {
            return new IpksTbl[size];
        }
    };
}
