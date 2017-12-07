package android.rezkyauliapratama.id.robusta.database;

import android.rezkyauliapratama.id.robusta.database.entity.IpksTbl;
import android.rezkyauliapratama.id.robusta.database.entity.IpksTblDao;

import java.util.List;

/**
 * Created by Rezky Aulia Pratama on 12/7/2017.
 */

public class ManageIpksTbl {


    private Facade facade;


    private IpksTblDao dao;

    ManageIpksTbl(Facade facade) {
        this.facade = facade;
        this.dao = facade.session.getIpksTblDao();
    }

    public long add(IpksTbl object) {
        return dao.insertOrReplace(object);
    }

    public void add(List<IpksTbl> object) {
        dao.insertOrReplaceInTx(object);
    }

    public List<IpksTbl> getAll() {
        return dao.queryBuilder().list();
    }

    public IpksTbl get(long id) {
        return dao.queryBuilder().where(IpksTblDao.Properties.Id.eq(id)).unique();
    }

    public void removeAll() {
        dao.deleteAll();
    }

    public void remove(IpksTbl object) {
        dao.delete(object);
    }

    public long size(){
        return dao.count();
    }

}
