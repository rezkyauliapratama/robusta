package android.rezkyauliapratama.id.robusta.database;

import android.rezkyauliapratama.id.robusta.database.entity.RsUmumTbl;
import android.rezkyauliapratama.id.robusta.database.entity.RsUmumTblDao;

import java.util.List;

/**
 * Created by Rezky Aulia Pratama on 12/7/2017.
 */

public class ManageRsUmumTbl {

    private Facade facade;


    private RsUmumTblDao dao;

    ManageRsUmumTbl(Facade facade) {
        this.facade = facade;
        this.dao = facade.session.getRsUmumTblDao();
    }

    public long add(RsUmumTbl object) {
        return dao.insertOrReplace(object);
    }

    public void add(List<RsUmumTbl> object) {
        dao.insertOrReplaceInTx(object);
    }

    public List<RsUmumTbl> getAll() {
        return dao.queryBuilder().list();
    }

    public RsUmumTbl get(long id) {
        return dao.queryBuilder().where(RsUmumTblDao.Properties.Id.eq(id)).unique();
    }

    public void removeAll() {
        dao.deleteAll();
    }

    public void remove(RsUmumTbl object) {
        dao.delete(object);
    }

    public long size(){
        return dao.count();
    }

}
