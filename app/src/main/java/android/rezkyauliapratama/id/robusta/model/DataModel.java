package android.rezkyauliapratama.id.robusta.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.rezkyauliapratama.id.robusta.database.entity.IpksTbl;
import android.rezkyauliapratama.id.robusta.database.entity.KursusTbl;
import android.rezkyauliapratama.id.robusta.database.entity.PuskesmasTbl;
import android.rezkyauliapratama.id.robusta.database.entity.RawanBencanaTbl;
import android.rezkyauliapratama.id.robusta.database.entity.RptraTbl;
import android.rezkyauliapratama.id.robusta.database.entity.RsKhususTbl;
import android.rezkyauliapratama.id.robusta.database.entity.RsUmumTbl;
import android.rezkyauliapratama.id.robusta.database.entity.SekolahTbl;
import android.rezkyauliapratama.id.robusta.model.api.Puskesmas;
import android.rezkyauliapratama.id.robusta.model.api.RPTRA;
import android.rezkyauliapratama.id.robusta.model.api.RsUmum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rezky Aulia Pratama on 12/7/2017.
 */

public class DataModel{
    private List<IpksTbl> ipksTbls;
    private List<KursusTbl> kursusTbls;
    private List<PuskesmasTbl> puskesmasTbls;
    private List<RawanBencanaTbl> rawanBencanaTbls;
    private List<RptraTbl> rptraTbls;
    private List<RsKhususTbl> rsKhususTbls;
    private List<RsUmumTbl> rsUmumTbls;
    private List<SekolahTbl> sekolahTbls;

    public DataModel() {
    }

    public DataModel(List<IpksTbl> ipksTbls, List<KursusTbl> kursusTbls, List<PuskesmasTbl> puskesmasTbls, List<RawanBencanaTbl> rawanBencanaTbls, List<RptraTbl> rptraTbls, List<RsKhususTbl> rsKhususTbls, List<RsUmumTbl> rsUmumTbls, List<SekolahTbl> sekolahTbls) {
        this.ipksTbls = ipksTbls;
        this.kursusTbls = kursusTbls;
        this.puskesmasTbls = puskesmasTbls;
        this.rawanBencanaTbls = rawanBencanaTbls;
        this.rptraTbls = rptraTbls;
        this.rsKhususTbls = rsKhususTbls;
        this.rsUmumTbls = rsUmumTbls;
        this.sekolahTbls = sekolahTbls;
    }

    public List<IpksTbl> getIpksTbls() {
        return ipksTbls;
    }

    public void setIpksTbls(List<IpksTbl> ipksTbls) {
        this.ipksTbls = ipksTbls;
    }

    public List<KursusTbl> getKursusTbls() {
        return kursusTbls;
    }

    public void setKursusTbls(List<KursusTbl> kursusTbls) {
        this.kursusTbls = kursusTbls;
    }

    public List<PuskesmasTbl> getPuskesmasTbls() {
        return puskesmasTbls;
    }

    public void setPuskesmasTbls(List<PuskesmasTbl> puskesmasTbls) {
        this.puskesmasTbls = puskesmasTbls;
    }

    public List<RawanBencanaTbl> getRawanBencanaTbls() {
        return rawanBencanaTbls;
    }

    public void setRawanBencanaTbls(List<RawanBencanaTbl> rawanBencanaTbls) {
        this.rawanBencanaTbls = rawanBencanaTbls;
    }

    public List<RptraTbl> getRptraTbls() {
        return rptraTbls;
    }

    public void setRptraTbls(List<RptraTbl> rptraTbls) {
        this.rptraTbls = rptraTbls;
    }

    public List<RsKhususTbl> getRsKhususTbls() {
        return rsKhususTbls;
    }

    public void setRsKhususTbls(List<RsKhususTbl> rsKhususTbls) {
        this.rsKhususTbls = rsKhususTbls;
    }

    public List<RsUmumTbl> getRsUmumTbls() {
        return rsUmumTbls;
    }

    public void setRsUmumTbls(List<RsUmumTbl> rsUmumTbls) {
        this.rsUmumTbls = rsUmumTbls;
    }

    public List<SekolahTbl> getSekolahTbls() {
        return sekolahTbls;
    }

    public void setSekolahTbls(List<SekolahTbl> sekolahTbls) {
        this.sekolahTbls = sekolahTbls;
    }


}
