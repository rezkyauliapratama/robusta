package android.rezkyauliapratama.id.robusta.database;

import android.rezkyauliapratama.id.robusta.database.entity.RsKhususTbl;
import android.rezkyauliapratama.id.robusta.database.entity.RsKhususTblDao;

import java.util.List;

/**
 * Created by Rezky Aulia Pratama on 12/7/2017.
 */

public class ManageRsKhususTbl {

    private Facade facade;


    private RsKhususTblDao dao;

    ManageRsKhususTbl(Facade facade) {
        this.facade = facade;
        this.dao = facade.session.getRsKhususTblDao();
    }

    public long add(RsKhususTbl object) {
        return dao.insertOrReplace(object);
    }

    public void add(List<RsKhususTbl> object) {
        dao.insertOrReplaceInTx(object);
    }

    public List<RsKhususTbl> getAll() {
        return dao.queryBuilder().list();
    }

    public RsKhususTbl get(long id) {
        return dao.queryBuilder().where(RsKhususTblDao.Properties.Id.eq(id)).unique();
    }

    public void removeAll() {
        dao.deleteAll();
    }

    public void remove(RsKhususTbl object) {
        dao.delete(object);
    }

    public long size(){
        return dao.count();
    }

}
