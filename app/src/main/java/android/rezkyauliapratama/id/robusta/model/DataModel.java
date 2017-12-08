package android.rezkyauliapratama.id.robusta.model;

import android.content.Context;
import android.rezkyauliapratama.id.robusta.R;
import android.rezkyauliapratama.id.robusta.database.Facade;
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
import android.rezkyauliapratama.id.robusta.model.api.RawanBencana;
import android.rezkyauliapratama.id.robusta.model.api.RsKhusus;
import android.rezkyauliapratama.id.robusta.model.api.RsUmum;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

/**
 * Created by Rezky Aulia Pratama on 12/7/2017.
 */

public class DataModel {
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

    public static DataModel init(final Context context) {
        Timber.e("inidata");
        InputStream in = context.getResources().openRawResource(R.raw.data_sekolah);
        Reader rd = new BufferedReader(new InputStreamReader(in));
        SekolahTbl[] arrSekolah = new Gson().fromJson(rd, SekolahTbl[].class);
        List<SekolahTbl> sekolahTbls = Arrays.asList(arrSekolah);

        in = context.getResources().openRawResource(R.raw.ipks);
        rd = new BufferedReader(new InputStreamReader(in));
        IpksTbl[] arrIpks = new Gson().fromJson(rd, IpksTbl[].class);
        List<IpksTbl> ipksTbls = Arrays.asList(arrIpks);

        in = context.getResources().openRawResource(R.raw.kursus);
        rd = new BufferedReader(new InputStreamReader(in));
        KursusTbl[] arrKursus = new Gson().fromJson(rd, KursusTbl[].class);
        List<KursusTbl> kursusTbls = Arrays.asList(arrKursus);

        in = context.getResources().openRawResource(R.raw.puskesmas);
        rd = new BufferedReader(new InputStreamReader(in));
        Puskesmas[] arrPuskesmas = new Gson().fromJson(rd, Puskesmas[].class);
        List<PuskesmasTbl> puskesmasTbls = new ArrayList<>();

        for (Puskesmas puskesmas : arrPuskesmas) {
            /*PuskesmasTbl(Long id, String namaPuskesmas, Double latitude,
                    Double longitude, String telepon, String faximile, String email,
                    String kepalaPuskesmas, Integer kodeKota, Integer kodeKecamatan,
                    Integer kodeKelurahan)*/
            PuskesmasTbl puskesmasTbl = new PuskesmasTbl(Long.parseLong(String.valueOf(puskesmas.getId())),
                    puskesmas.getNamaPuskesmas(), puskesmas.getLocation().getLatitude(), puskesmas.getLocation().getLongitude()
                    , (puskesmas.getTelepon().size() > 0) ? puskesmas.getTelepon().get(0) : "", (puskesmas.getFaximile().size() > 0) ? puskesmas.getFaximile().get(0) : "", puskesmas.getEmail(), puskesmas.getKepalaPuskesmas(), puskesmas.getKodeKota(), puskesmas.getKodeKecamatan()
                    , puskesmas.getKodeKelurahan());

            if (puskesmasTbl != null) {
                puskesmasTbls.add(puskesmasTbl);
            }
        }

        in = context.getResources().openRawResource(R.raw.rawan_bencana);
        rd = new BufferedReader(new InputStreamReader(in));
        RawanBencana[] arrRawanBencana = new Gson().fromJson(rd, RawanBencana[].class);
        List<RawanBencanaTbl> rawanBencanaTbls = new ArrayList<>();

        for (RawanBencana rawanBencana : arrRawanBencana) {
            RawanBencanaTbl rawanBencanaTbl = new RawanBencanaTbl(Long.parseLong(String.valueOf(rawanBencana.getId())), rawanBencana.getBencana(), rawanBencana.getLokasi(), rawanBencana.getKecamatan(),
                    rawanBencana.getKota(), rawanBencana.getLocation().getLatitude(), rawanBencana.getLocation().getLongitude());

            if (rawanBencanaTbl != null) {
                rawanBencanaTbls.add(rawanBencanaTbl);
            }
        }

        in = context.getResources().openRawResource(R.raw.rptra);
        rd = new BufferedReader(new InputStreamReader(in));
        RPTRA[] arrRptra = new Gson().fromJson(rd, RPTRA[].class);
        List<RptraTbl> rptraTbls = new ArrayList<>();

        for (RPTRA rptra : arrRptra) {
            RptraTbl rptraTbl = new RptraTbl(
                    Long.parseLong(String.valueOf(rptra.getId())), rptra.getNamaRptra(), rptra.getAlamat(), rptra.getKelurahan(), rptra.getKecamatan(), rptra.getLuas(), rptra.getWaktuPeresmian(),
                    rptra.getTelepon(), rptra.getEmail(), rptra.getFasilitas(), rptra.getPermasalahan(), rptra.getLocation().getLatitude(), rptra.getLocation().getLongitude()
            );

            if (rptraTbl != null) {
                rptraTbls.add(rptraTbl);
            }
        }

        in = context.getResources().openRawResource(R.raw.rs_khusus);
        rd = new BufferedReader(new InputStreamReader(in));
        RsKhusus[] arrRsKhusus = new Gson().fromJson(rd, RsKhusus[].class);
        List<RsKhususTbl> rsKhususTbls = new ArrayList<>();

        for (RsKhusus rsKhusus : arrRsKhusus) {
            RsKhususTbl rsKhususTbl = new RsKhususTbl(
                    Long.parseLong(String.valueOf(rsKhusus.getId())), rsKhusus.getNamaRsk(), rsKhusus.getJenisRsk(), rsKhusus.getKodePos(), rsKhusus.getTelepon().size() > 0 ? rsKhusus.getTelepon().get(0) : ""
                    , rsKhusus.getFaximile().size() > 0 ? rsKhusus.getFaximile().get(0) : "",
                    rsKhusus.getWebsite(), rsKhusus.getEmail(), rsKhusus.getKodeKota(), rsKhusus.getKodeKecamatan(), rsKhusus.getKodeKelurahan(),
                    rsKhusus.getLatitude(), rsKhusus.getLongitude()
            );

            if (rsKhususTbl != null) {
                rsKhususTbls.add(rsKhususTbl);
            }
        }

        in = context.getResources().openRawResource(R.raw.rs_khusus);
        rd = new BufferedReader(new InputStreamReader(in));
        RsUmum[] arrRsUmum = new Gson().fromJson(rd, RsUmum[].class);
        List<RsUmumTbl> rsUmumTbls = new ArrayList<>();

        for (RsUmum rsUmum : arrRsUmum) {
            RsUmumTbl rsUmumTbl = new RsUmumTbl(
                    Long.parseLong(String.valueOf(rsUmum.getId())), rsUmum.getNamaRsu(), rsUmum.getJenisRsu(), rsUmum.getKodePos(), rsUmum.getTelepon().size() > 0 ? rsUmum.getTelepon().get(0) : ""
                    , rsUmum.getFaximile().size() > 0 ? rsUmum.getFaximile().get(0) : "",
                    rsUmum.getWebsite(), rsUmum.getEmail(), rsUmum.getKodeKota(), rsUmum.getKodeKecamatan(), rsUmum.getKodeKelurahan(),
                    rsUmum.getLatitude(), rsUmum.getLongitude()
            );

            if (rsUmumTbl != null) {
                rsUmumTbls.add(rsUmumTbl);
            }
        }

        DataModel dataModel = new DataModel();
        dataModel.setIpksTbls(ipksTbls);
        dataModel.setKursusTbls(kursusTbls);
        dataModel.setPuskesmasTbls(puskesmasTbls);
        dataModel.setSekolahTbls(sekolahTbls);
        dataModel.setRsKhususTbls(rsKhususTbls);
        dataModel.setRsUmumTbls(rsUmumTbls);
        dataModel.setRptraTbls(rptraTbls);
        dataModel.setRawanBencanaTbls(rawanBencanaTbls);

        Facade facade = Facade.getInstance();
        facade.getManageIpksTbl().add(ipksTbls);
        facade.getManageRawanBencanaTbl().add(rawanBencanaTbls);
        facade.getManageKursusTbl().add(kursusTbls);
        facade.getManagePuskesmasTbl().add(puskesmasTbls);
        facade.getManageSekolahTbl().add(sekolahTbls);
        facade.getManageRsKhususTbl().add(rsKhususTbls);
        facade.getManageRsUmumTbl().add(rsUmumTbls);
        facade.getManageRptraTbl().add(rptraTbls);

        return dataModel;
    }

    public static DataModel getOrInitDataModel(Context context) {
        DataModel dataModel;
        Facade facade = Facade.getInstance();
        if (facade.isDataInitialized()) {
            dataModel = new DataModel();
            dataModel.setIpksTbls(facade.getManageIpksTbl().getAll());
            dataModel.setKursusTbls(facade.getManageKursusTbl().getAll());
            dataModel.setPuskesmasTbls(facade.getManagePuskesmasTbl().getAll());
            dataModel.setSekolahTbls(facade.getManageSekolahTbl().getAll());
            dataModel.setRsKhususTbls(facade.getManageRsKhususTbl().getAll());
            dataModel.setRsUmumTbls(facade.getManageRsUmumTbl().getAll());
            dataModel.setRptraTbls(facade.getManageRptraTbl().getAll());
            dataModel.setRawanBencanaTbls(facade.getManageRawanBencanaTbl().getAll());
        } else {
            return init(context);
        }
        return dataModel;
    }
}
