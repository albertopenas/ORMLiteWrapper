package es.sw.activerecordapp.samples;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.sw.activerecordapp.oldddbb.query.AbsJoinQuery;
import es.sw.activerecordapp.model.FeatureOLDEntity;
import es.sw.activerecordapp.model.VersionOLDEntity;

/**
 * Created by alberto on 13/10/15.
 */
public class JoinQuery extends AbsJoinQuery<VersionOLDEntity, FeatureOLDEntity, List<VersionOLDEntity>> {

    public JoinQuery(Class aClass, Class bClazz) {
        super(aClass, bClazz);
    }

    @Override
    protected List<VersionOLDEntity> query(Dao<VersionOLDEntity, Integer> daoA, Dao<FeatureOLDEntity, Integer> daoB) {
        List<VersionOLDEntity>versionTableList = null;
        try {
            versionTableList = daoA.queryBuilder().join(daoB.queryBuilder()).query();
        } catch (SQLException e) {
            e.printStackTrace();
            versionTableList = new ArrayList<>();
        }
        return versionTableList;
    }
}
