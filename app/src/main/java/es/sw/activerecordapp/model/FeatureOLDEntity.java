package es.sw.activerecordapp.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import es.sw.activerecordapp.oldddbb.model.OLDEntity;

/**
 * Created by alberto on 11/10/15.
 */
@DatabaseTable
public class FeatureOLDEntity extends OLDEntity {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String name;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = false)
    private VersionOLDEntity versionTable;

    public FeatureOLDEntity() {}

    public FeatureOLDEntity(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersionTable(VersionOLDEntity versionTable) {
        this.versionTable = versionTable;
    }
}
