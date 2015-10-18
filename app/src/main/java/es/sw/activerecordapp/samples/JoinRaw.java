package es.sw.activerecordapp.samples;

import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import es.sw.activerecordapp.model.FeatureOLDEntity;
import es.sw.activerecordapp.model.VersionOLDEntity;

/**
 * Created by alberto on 11/10/15.
 */
public class JoinRaw {

    private static final String TAG = JoinRaw.class.getSimpleName();

    private Dao<VersionOLDEntity, Integer> vDao;
    private Dao<FeatureOLDEntity, Integer> fDao;

    public JoinRaw() {
        //this.vDao =  new DaoManager().getDaoFor(VersionOLDEntity.class);
        //this.fDao =  new DaoManager().getDaoFor(FeatureOLDEntity.class);
    }


    public List<VersionOLDEntity> fetch(){
        QueryBuilder<VersionOLDEntity, Integer> vqb = vDao.queryBuilder();
        QueryBuilder<FeatureOLDEntity, Integer> fqb = fDao.queryBuilder();
        List<VersionOLDEntity> results = null;
        try {
            //CHOLAN LAS DOS
            //results = vqb.leftJoin(fqb).query();// DEVUELVE LA FEATURE SIN EL VERSION... NO CHOLA
            //Log.d(TAG, "size: " + results.size());
            results = vqb.join(fqb).query();
            Log.d(TAG, "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;


    }
}
