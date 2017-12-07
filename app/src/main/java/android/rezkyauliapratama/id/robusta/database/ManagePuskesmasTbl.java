package android.rezkyauliapratama.id.robusta.database;

import android.rezkyauliapratama.id.robusta.database.entity.PuskesmasTbl;
import android.rezkyauliapratama.id.robusta.database.entity.PuskesmasTblDao;

import java.util.List;

/**
 * Created by Rezky Aulia Pratama on 12/7/2017.
 */

public class ManagePuskesmasTbl {

    private Facade facade;


    private PuskesmasTblDao dao;

    ManagePuskesmasTbl(Facade facade) {
        this.facade = facade;
        this.dao = facade.session.getPuskesmasTblDao();
    }

    public long add(PuskesmasTbl object) {
        return dao.insertOrReplace(object);
    }

    public void add(List<PuskesmasTbl> object) {
        dao.insertOrReplaceInTx(object);
    }

    public List<PuskesmasTbl> getAll() {
        return dao.queryBuilder().list();
    }

    public PuskesmasTbl get(long id) {
        return dao.queryBuilder().where(PuskesmasTblDao.Properties.Id.eq(id)).unique();
    }

    public void removeAll() {
        dao.deleteAll();
    }

    public void remove(PuskesmasTbl object) {
        dao.delete(object);
    }

    public long size(){
        return dao.count();
    }

}
