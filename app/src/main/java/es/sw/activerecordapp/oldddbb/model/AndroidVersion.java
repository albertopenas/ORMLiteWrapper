package es.sw.activerecordapp.oldddbb.model;

import android.content.Context;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

import es.sw.activerecordapp.oldddbb.ActiveRecord;
import rx.Observable;

/**
 * Created by alberto on 23/9/15.
 */
@DatabaseTable
public class AndroidVersion extends ActiveRecord {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private int versionCode;

    @DatabaseField
    private String versionName;

    public AndroidVersion() {}

    public AndroidVersion(int versionCode, String versionName) {
        this.versionCode = versionCode;
        this.versionName = versionName;
    }

    public long getId() {
        return id;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    @Override
    public String toString() {
        return "version code: " + versionCode + ", version name: " + versionName;
    }

    public static List<AndroidVersion> findAll(Context ctx){
        return findAll(ctx, AndroidVersion.class);
    }

    public static AndroidVersion findById(Context ctx, int id){
        return findById(ctx, AndroidVersion.class, id);
    }

    public Observable<Boolean> saveRX(Context context){
        return saveRX(context, this);
    }

    public Observable<Boolean> saveWithExRX(){
        return saveWithExceptionRX();
    }
}
