package android.rezkyauliapratama.id.robusta.database;

import android.rezkyauliapratama.id.robusta.database.entity.RptraTbl;
import android.rezkyauliapratama.id.robusta.database.entity.RptraTblDao;

import java.util.List;

/**
 * Created by Rezky Aulia Pratama on 12/7/2017.
 */

public class ManageRptraTbl {

    private Facade facade;


    private RptraTblDao dao;

    ManageRptraTbl(Facade facade) {
        this.facade = facade;
        this.dao = facade.session.getRptraTblDao();
    }

    public long add(RptraTbl object) {
        return dao.insertOrReplace(object);
    }

    public void add(List<RptraTbl> object) {
        dao.insertOrReplaceInTx(object);
    }

    public List<RptraTbl> getAll() {
        return dao.queryBuilder().list();
    }

    public RptraTbl get(long id) {
        return dao.queryBuilder().where(RptraTblDao.Properties.Id.eq(id)).unique();
    }

    public void removeAll() {
        dao.deleteAll();
    }

    public void remove(RptraTbl object) {
        dao.delete(object);
    }

    public long size(){
        return dao.count();
    }

}
