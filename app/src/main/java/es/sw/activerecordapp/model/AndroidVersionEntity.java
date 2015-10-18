package es.sw.activerecordapp.model;

import com.j256.ormlite.field.DatabaseField;

import es.sw.activerecordapp.database.model.Entity;

/**
 * Created by alberto on 27/9/15.
 */
public class AndroidVersionEntity extends Entity {


    public static final String COLUMN_VERSION_NAME = "column_name";


    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private int versionCode;

    @DatabaseField(columnName = COLUMN_VERSION_NAME)
    private String versionName;

    public AndroidVersionEntity() {}

    public AndroidVersionEntity(int versionCode, String versionName) {
        this.versionCode = versionCode;
        this.versionName = versionName;
    }

    public long getId() {
        return id;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    @Override
    public String toString() {
        return "Version name: " + versionName;
    }

}
