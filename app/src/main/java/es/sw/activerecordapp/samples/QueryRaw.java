package es.sw.activerecordapp.samples;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.sw.activerecordapp.model.VersionOLDEntity;

/**
 * Created by alberto on 11/10/15.
 */
public class QueryRaw {
    private Dao<VersionOLDEntity, Integer>dao;

    public QueryRaw() {
        //this.dao =  new DaoManager().getDaoFor(VersionOLDEntity.class);
    }

    public List<VersionOLDEntity> fetch(){
        GenericRawResults<VersionOLDEntity> rawResults = null;
        try {
            rawResults = dao.queryRaw("SELECT * FROM VersionTable", dao.getRawRowMapper(), new String[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<VersionOLDEntity>versionTables = new ArrayList<>();
        for (VersionOLDEntity versionTable: rawResults) {
            versionTables.add(versionTable);
        }
        try {
            rawResults.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return versionTables;
    }
}
