package android.rezkyauliapratama.id.robusta.database;

import android.rezkyauliapratama.id.robusta.database.entity.DaoSession;

/**
 * Created by Rezky Aulia Pratama on 12/7/2017.
 */

public class Facade {
    private static Facade instance;
    final DaoSession session;

    public static void init(DaoSession daoSession) {
        instance = new Facade(daoSession);
    }

    public static Facade getInstance() {
        return instance;
    }

    private final ManageIpksTbl manageIpksTbl;
    private final ManageKursusTbl manageKursusTbl;
    private final ManagePuskesmasTbl managePuskesmasTbl;
    private final ManageRawanBencanaTbl manageRawanBencanaTbl;
    private final ManageRptraTbl manageRptraTbl;
    private final ManageRsKhususTbl manageRsKhususTbl;
    private final ManageRsUmumTbl manageRsUmumTbl;
    private final ManageSekolahTbl manageSekolahTbl;

    Facade(DaoSession daoSession) {
        this.session = daoSession;

        manageRptraTbl = new ManageRptraTbl(this);
        manageIpksTbl = new ManageIpksTbl(this);
        manageKursusTbl = new ManageKursusTbl(this);
        managePuskesmasTbl = new ManagePuskesmasTbl(this);
        manageRawanBencanaTbl = new ManageRawanBencanaTbl(this);
        manageRsKhususTbl = new ManageRsKhususTbl(this);
        manageSekolahTbl = new ManageSekolahTbl(this);
        manageRsUmumTbl = new ManageRsUmumTbl(this);
    }

    public ManageIpksTbl getManageIpksTbl() {
        return manageIpksTbl;
    }

    public ManageKursusTbl getManageKursusTbl() {
        return manageKursusTbl;
    }

    public ManagePuskesmasTbl getManagePuskesmasTbl() {
        return managePuskesmasTbl;
    }

    public ManageRawanBencanaTbl getManageRawanBencanaTbl() {
        return manageRawanBencanaTbl;
    }

    public ManageRptraTbl getManageRptraTbl() {
        return manageRptraTbl;
    }

    public ManageRsKhususTbl getManageRsKhususTbl() {
        return manageRsKhususTbl;
    }

    public ManageRsUmumTbl getManageRsUmumTbl() {
        return manageRsUmumTbl;
    }

    public ManageSekolahTbl getManageSekolahTbl() {
        return manageSekolahTbl;
    }

    public boolean isDataInitialized() {
        return getManageSekolahTbl().getAll().size() > 0;
    }

}
