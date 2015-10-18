package es.sw.activerecordapp.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import es.sw.activerecordapp.oldddbb.model.OLDEntity;
import rx.Observable;

/**
 * Created by alberto on 27/9/15.
 */
@DatabaseTable
public class AndroidOLDEntity extends OLDEntity {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String versionName;

    public AndroidOLDEntity() {}

    public AndroidOLDEntity(String versionName) {
        this.versionName = versionName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public static Observable<AndroidOLDEntity> findByIdRX(Integer id){
        return findByIdRX(AndroidOLDEntity.class, id);
    }

    public static Observable<Boolean> saveRX(AndroidOLDEntity t){
        return saveRX(AndroidOLDEntity.class, t);
    }
}
