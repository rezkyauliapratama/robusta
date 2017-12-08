package android.rezkyauliapratama.id.robusta.database;

import android.rezkyauliapratama.id.robusta.database.entity.SekolahTbl;
import android.rezkyauliapratama.id.robusta.database.entity.SekolahTblDao;

import java.util.List;

/**
 * Created by Rezky Aulia Pratama on 12/7/2017.
 */

public class ManageSekolahTbl {

    private Facade facade;


    private SekolahTblDao dao;

    ManageSekolahTbl(Facade facade) {
        this.facade = facade;
        this.dao = facade.session.getSekolahTblDao();
    }

    public long add(SekolahTbl object) {
        return dao.insertOrReplace(object);
    }

    public void add(List<SekolahTbl> object) {
        dao.insertOrReplaceInTx(object);
    }

    public List<SekolahTbl> getAll() {
        return dao.queryBuilder().list();
    }

    public SekolahTbl get(long id) {
        return dao.queryBuilder().where(SekolahTblDao.Properties.Id.eq(id)).unique();
    }

    public SekolahTbl get(String name) {
        return dao.queryBuilder().where(SekolahTblDao.Properties.NamaSekolah.eq(name)).unique();
    }

    public void removeAll() {
        dao.deleteAll();
    }

    public void remove(SekolahTbl object) {
        dao.delete(object);
    }

    public long size(){
        return dao.count();
    }

}
