package android.rezkyauliapratama.id.robusta.database;

import android.rezkyauliapratama.id.robusta.database.entity.KursusTbl;
import android.rezkyauliapratama.id.robusta.database.entity.KursusTblDao;

import java.util.List;

/**
 * Created by Rezky Aulia Pratama on 12/7/2017.
 */

public class ManageKursusTbl {

    private Facade facade;


    private KursusTblDao dao;

    ManageKursusTbl(Facade facade) {
        this.facade = facade;
        this.dao = facade.session.getKursusTblDao();
    }

    public long add(KursusTbl object) {
        return dao.insertOrReplace(object);
    }

    public void add(List<KursusTbl> object) {
        dao.insertOrReplaceInTx(object);
    }

    public List<KursusTbl> getAll() {
        return dao.queryBuilder().list();
    }

    public KursusTbl get(long id) {
        return dao.queryBuilder().where(KursusTblDao.Properties.Id.eq(id)).unique();
    }

    public void removeAll() {
        dao.deleteAll();
    }

    public void remove(KursusTbl object) {
        dao.delete(object);
    }

    public long size(){
        return dao.count();
    }

}
