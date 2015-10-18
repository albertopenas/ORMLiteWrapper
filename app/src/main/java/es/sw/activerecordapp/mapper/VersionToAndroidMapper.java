package es.sw.activerecordapp.mapper;

import java.util.concurrent.Callable;

import es.sw.activerecordapp.model.AndroidOLDEntity;
import es.sw.activerecordapp.model.VersionOLDEntity;
import es.sw.activerecordapp.rx.RxHelper;
import rx.Observable;

/**
 * Created by alberto on 27/9/15.
 */
public class VersionToAndroidMapper {

    public static Observable<AndroidOLDEntity>map(VersionOLDEntity versionTable){
        return RxHelper.createObservable(new Callable<AndroidOLDEntity>() {
            @Override
            public AndroidOLDEntity call() throws Exception {
                return new AndroidOLDEntity(versionTable.getVersionName());
            }
        });
    }
}
