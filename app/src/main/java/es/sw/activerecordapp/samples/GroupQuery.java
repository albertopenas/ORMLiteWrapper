package es.sw.activerecordapp.samples;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import es.sw.activerecordapp.oldddbb.query.AbsQuery;
import es.sw.activerecordapp.model.VersionOLDEntity;

/**
 * Created by alberto on 12/10/15.
 */
public class GroupQuery extends AbsQuery<VersionOLDEntity, List<VersionOLDEntity>> {

    public GroupQuery(Class aClass) {
        super(aClass);
    }

    @Override
    protected List<VersionOLDEntity> query(Dao<VersionOLDEntity, Integer> dao) {
        QueryBuilder<VersionOLDEntity, Integer> queryBuilderV = dao.queryBuilder();

        List<VersionOLDEntity>list = null;
        try {
            list = queryBuilderV.groupBy(VersionOLDEntity.COLUMN_VERSION_NAME).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
