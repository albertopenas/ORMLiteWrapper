package es.sw.activerecordapp.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import es.sw.activerecordapp.oldddbb.model.OLDEntity;
import rx.Observable;

/**
 * Created by alberto on 27/9/15.
 */
@DatabaseTable
public class VersionOLDEntity extends OLDEntity {

    public static final String COLUMN_VERSION_NAME = "version_name";

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private int versionCode;

    @DatabaseField(columnName = COLUMN_VERSION_NAME)
    private String versionName;

    @ForeignCollectionField(eager = false)
    private ForeignCollection<FeatureOLDEntity> lazyForeignCollection;

    public VersionOLDEntity() {}

    public VersionOLDEntity(int versionCode, String versionName) {
        this.versionCode = versionCode;
        this.versionName = versionName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    @Override
    public String toString() {
        return "version code: " + versionCode + ", version name: " + versionName;
    }

    public static Observable<VersionOLDEntity> findByIdRX(Integer id){
        return findByIdRX(VersionOLDEntity.class, id);
    }

    public static Observable<Boolean> saveRX(VersionOLDEntity t){
        return saveRX(VersionOLDEntity.class, t);
    }


    public ForeignCollection<FeatureOLDEntity> getLazyForeignCollection() {
        return lazyForeignCollection;
    }

    public static<T extends OLDEntity> boolean trySave(T t){
        return trySave(VersionOLDEntity.class, t);
    }
}
