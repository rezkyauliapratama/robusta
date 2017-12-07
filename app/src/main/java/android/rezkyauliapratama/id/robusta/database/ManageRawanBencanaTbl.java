package android.rezkyauliapratama.id.robusta.database;

import android.rezkyauliapratama.id.robusta.database.entity.RawanBencanaTbl;
import android.rezkyauliapratama.id.robusta.database.entity.RawanBencanaTblDao;

import java.util.List;

/**
 * Created by Rezky Aulia Pratama on 12/7/2017.
 */

public class ManageRawanBencanaTbl {

    private Facade facade;


    private RawanBencanaTblDao dao;

    ManageRawanBencanaTbl(Facade facade) {
        this.facade = facade;
        this.dao = facade.session.getRawanBencanaTblDao();
    }

    public long add(RawanBencanaTbl object) {
        return dao.insertOrReplace(object);
    }

    public void add(List<RawanBencanaTbl> object) {
        dao.insertOrReplaceInTx(object);
    }

    public List<RawanBencanaTbl> getAll() {
        return dao.queryBuilder().list();
    }

    public RawanBencanaTbl get(long id) {
        return dao.queryBuilder().where(RawanBencanaTblDao.Properties.Id.eq(id)).unique();
    }

    public void removeAll() {
        dao.deleteAll();
    }

    public void remove(RawanBencanaTbl object) {
        dao.delete(object);
    }

    public long size(){
        return dao.count();
    }

}
