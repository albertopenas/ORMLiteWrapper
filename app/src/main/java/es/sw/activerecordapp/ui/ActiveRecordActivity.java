package es.sw.activerecordapp.ui;

import android.os.Bundle;
import android.util.Log;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import es.sw.activerecordapp.R;
import es.sw.activerecordapp.database.core.BulkTransaction;
import es.sw.activerecordapp.database.exception.NotFoundException;
import es.sw.activerecordapp.database.query.Save;
import es.sw.activerecordapp.database.query.Where;
import es.sw.activerecordapp.mapper.VersionToAndroidMapper;
import es.sw.activerecordapp.model.AndroidOLDEntity;
import es.sw.activerecordapp.model.AndroidVersionEntity;
import es.sw.activerecordapp.model.FeatureOLDEntity;
import es.sw.activerecordapp.model.VersionOLDEntity;
import es.sw.activerecordapp.oldddbb.core.Select;
import es.sw.activerecordapp.oldddbb.model.AndroidVersion;
import es.sw.activerecordapp.samples.GroupQuery;
import es.sw.activerecordapp.samples.JoinQuery;
import es.sw.activerecordapp.samples.JoinRaw;
import es.sw.activerecordapp.samples.QueryRaw;
import es.sw.activerecordapp.samples.VersionEntityWhere;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//TODO: meter botones etc.. con edit texts y un recicler o algo para ver las cosas que se van metiendo
//TODO: retrolambda -> https://github.com/evant/gradle-retrolambda
//TODO: ui observables -> https://github.com/ReactiveX/RxAndroid/wiki
//TODO: androidObservables -> https://github.com/trello/RxLifecycle

//TODO: machacar el DaoManager!!!!
//TODO: machacar el DaoManager!!!!
//TODO: machacar el DaoManager!!!!
//TODO: machacar el DaoManager!!!!
//TODO: machacar el DaoManager!!!!


/*
    TODO: enable ORLMLITE LOG
    adb shell setprop log.tag.ORMLite DEBUG

 */
public class ActiveRecordActivity extends RxAppCompatActivity {

    private final static String TAG = ActiveRecordActivity.class.getSimpleName();
    long startnow;
    long endnow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_scrolling);

        //TEST RX CHAIN
        //testActiveRecordWithObservablesChain();
        //testTableWithObservableChain();

        //BULK TESTS VS NON BULK
        //testSave();
        //testSaveList();
        //testSaveBulk();

        //SELECT TEST FOR AVOID REWRITING STATIC ACCESSES INSIDE XXXTable clases
        //testSelect();

        //TEST RAW QUERY OK!!!!!!!!!!
        //testRaw();

        //TEST JOIN RAW
        //testJoinRaw();

        //TEST NEW MANUAL QUERYS
        //testNewQuerys();

        //TEST LATEST QUERYS
        //testLatestQuerys();

        //TEST CREATE NEW ENTITY
        testNewEntity();
    }

    private void testNewEntity() {
        AndroidVersionEntity androidVersionEntity = new AndroidVersionEntity(16, "Jelly Bean");
        Save<AndroidVersionEntity> saveQuery = new Save<>(androidVersionEntity);
        try {
            boolean saved = saveQuery.run();
            Log.d(TAG,"");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Where<AndroidVersionEntity>where = new Where<>(AndroidVersionEntity.class, AndroidVersionEntity.COLUMN_VERSION_NAME, "Jelly Bean");
        try {
            List<AndroidVersionEntity> list1 = where.run();
            Log.d(TAG, "size:"  + list1.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testLatestQuerys(){
        VersionOLDEntity versionTable = new VersionOLDEntity(22, "Lollipop");
        boolean saved = VersionOLDEntity.trySave(VersionOLDEntity.class, versionTable);

        VersionOLDEntity versionTable2 = new VersionOLDEntity(19, "Kitkat");
        saved = VersionOLDEntity.trySave(VersionOLDEntity.class, versionTable2);

        VersionOLDEntity versionTable3 = new VersionOLDEntity(23, "MarshMallow");
        saved = VersionOLDEntity.trySave(VersionOLDEntity.class, versionTable3);

        VersionEntityWhere where = new VersionEntityWhere(VersionOLDEntity.class, VersionOLDEntity.COLUMN_VERSION_NAME, "Kitkat");
        try {
            List<VersionOLDEntity> list = where.run();
            Log.d(TAG, "size:"  + list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Where<VersionOLDEntity>where1 = new Where<>(VersionOLDEntity.class, VersionOLDEntity.COLUMN_VERSION_NAME, "Kitkat");
        try {
            List<VersionOLDEntity> list1 = where1.run();
            Log.d(TAG, "size:"  + list1.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testNewQuerys() {
        VersionOLDEntity versionTable = new VersionOLDEntity(22, "Lollipop");
        boolean saved = VersionOLDEntity.trySave(VersionOLDEntity.class, versionTable);

        FeatureOLDEntity featureTable = new FeatureOLDEntity();
        featureTable.setName("Material Design");
        featureTable.setVersionTable(versionTable);
        featureTable.trySave(FeatureOLDEntity.class, featureTable);

        FeatureOLDEntity featureTable1 = new FeatureOLDEntity();
        featureTable1.setName("Notificaciones");
        featureTable1.setVersionTable(versionTable);
        featureTable1.trySave(FeatureOLDEntity.class, featureTable1);

        versionTable = new VersionOLDEntity(22, "Lollipop");
        saved = VersionOLDEntity.trySave(VersionOLDEntity.class, versionTable);

        FeatureOLDEntity featureTable2 = new FeatureOLDEntity();
        featureTable2.setName("Batería");
        featureTable2.setVersionTable(versionTable);
        featureTable2.trySave(FeatureOLDEntity.class, featureTable2);

        FeatureOLDEntity featureTable3 = new FeatureOLDEntity();
        featureTable3.setName("Seguridad");
        featureTable3.setVersionTable(versionTable);
        featureTable3.trySave(FeatureOLDEntity.class, featureTable3);



        VersionOLDEntity versionTable2 = new VersionOLDEntity(23, "MarshMallow");
        saved = VersionOLDEntity.trySave(VersionOLDEntity.class, versionTable2);

        FeatureOLDEntity featureTable4 = new FeatureOLDEntity();
        featureTable4.setName("App permissions");
        featureTable4.setVersionTable(versionTable2);
        featureTable4.trySave(FeatureOLDEntity.class, featureTable4);

        FeatureOLDEntity featureTable5 = new FeatureOLDEntity();
        featureTable5.setName("Web experience");
        featureTable5.setVersionTable(versionTable2);
        featureTable5.trySave(FeatureOLDEntity.class, featureTable5);

        VersionOLDEntity versionTable1 = new VersionOLDEntity(23, "MarshMallow");
        saved = VersionOLDEntity.trySave(VersionOLDEntity.class, versionTable1);

        FeatureOLDEntity featureTable6 = new FeatureOLDEntity();
        featureTable6.setName("Fingerprint support");
        featureTable6.setVersionTable(versionTable1);
        featureTable6.trySave(FeatureOLDEntity.class, featureTable6);

        FeatureOLDEntity featureTable7 = new FeatureOLDEntity();
        featureTable7.setName("Mobile payments");
        featureTable7.setVersionTable(versionTable1);
        featureTable7.trySave(FeatureOLDEntity.class, featureTable7);

        GroupQuery query = new GroupQuery(VersionOLDEntity.class);
        List<VersionOLDEntity> lista = query.run();
        Log.d(TAG, "size: " + lista.size());

        try {
            List<VersionOLDEntity>versionTableList = VersionOLDEntity.tryFindAll(VersionOLDEntity.class);
            Log.d(TAG, "size: " + versionTableList.size());
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        JoinQuery joinQuery = new JoinQuery(VersionOLDEntity.class, FeatureOLDEntity.class);
        List<VersionOLDEntity> versionTableList = joinQuery.run();
        Log.d(TAG, "");
    }

    private void testJoinRaw() {

        VersionOLDEntity versionTable = new VersionOLDEntity(23, "MarshMallow");
        boolean saved = VersionOLDEntity.trySave(VersionOLDEntity.class, versionTable);

        FeatureOLDEntity featureTable = new FeatureOLDEntity("Material Design");
        featureTable.setVersionTable(versionTable);
        boolean saved2 = FeatureOLDEntity.trySave(FeatureOLDEntity.class, featureTable);

        JoinRaw joinRaw = new JoinRaw();
        List<VersionOLDEntity> versionTables = joinRaw.fetch();
        Log.d(TAG, "versionTables size: " + versionTables.size());
    }

    private void testRaw() {
        VersionOLDEntity versionTable = new VersionOLDEntity(1, "version");
        boolean saved = VersionOLDEntity.trySave(versionTable);

        QueryRaw queryRaw = new QueryRaw();
        List<VersionOLDEntity> list = queryRaw.fetch();

    }

    private void testSelect() {
        VersionOLDEntity versionTable = new VersionOLDEntity(1, "version");
        VersionOLDEntity.trySave(versionTable);

        Select<VersionOLDEntity>select = new Select<VersionOLDEntity>().from(VersionOLDEntity.class);
        try {
            VersionOLDEntity findedVersionTable = select.findById(1);
            Log.e(TAG, "CORONACIONNNNNN!!!!");
            Log.d(TAG, findedVersionTable.toString());
            Log.e(TAG, "CORONACIONNNNNN!!!!");
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    private void testSaveList() {
        //09-29 23:00:07.884 7846-7846/es.sw.activerecordapp D/MYTAG: Excution time: 194 ms
        List<VersionOLDEntity> versionTableList = new ArrayList<>();
        for (int i=0;i<100;i++){
            VersionOLDEntity versionTable = new VersionOLDEntity(1, "version");
            versionTableList.add(versionTable);
        }

        startnow = android.os.SystemClock.uptimeMillis();
        VersionOLDEntity.trySave(VersionOLDEntity.class, versionTableList);
        endnow = android.os.SystemClock.uptimeMillis();
        Log.d("MYTAG", "Excution time: " + (endnow - startnow) + " ms");
    }

    private void testSave(){
        //09-29 22:58:47.680 6044-6044/es.sw.activerecordapp D/MYTAG: Excution time: 1131 ms
        List<VersionOLDEntity> versionTableList = new ArrayList<>();
        for (int i=0;i<100;i++){
            VersionOLDEntity versionTable = new VersionOLDEntity(1, "version");
            versionTableList.add(versionTable);
        }

        startnow = android.os.SystemClock.uptimeMillis();
        for(VersionOLDEntity versionTable:versionTableList){
            VersionOLDEntity.trySave(VersionOLDEntity.class, versionTable);
        }
        endnow = android.os.SystemClock.uptimeMillis();
        Log.d("MYTAG", "Excution time: "+(endnow-startnow)+" ms");
    }

    private void testSaveBulk() {
        //DEL THREAD AL MAIN: 09-29 23:03:08.447 31577-31577/es.sw.activerecordapp D/MYTAG: Excution time: 798 ms
        //EN EL PROPIO THREAD: 09-29 23:05:05.707 1833-1910/es.sw.activerecordapp D/MYTAG: Excution time: 163 ms
        List<VersionOLDEntity> versionTableList = new ArrayList<>();
        for (int i=0;i<100;i++){
            VersionOLDEntity versionTable = new VersionOLDEntity(1, "version");
            versionTableList.add(versionTable);
        }

        BulkTransaction<Boolean> bulkSave = new BulkTransaction();
        /* NON STATIC
        bulkSave.makeRX(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                startnow = android.os.SystemClock.uptimeMillis();
                for (VersionTable versionTable : versionTableList) {
                    VersionTable.save(VersionTable.class, versionTable);
                }
                endnow = android.os.SystemClock.uptimeMillis();
                Log.d("MYTAG", "Excution time: " + (endnow - startnow) + " ms");
                return true;
            }
        })*/
        // STATIC APPROACH
        BulkTransaction.makeTransactionRX(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                startnow = android.os.SystemClock.uptimeMillis();
                for (VersionOLDEntity versionTable : versionTableList) {
                    VersionOLDEntity.save(VersionOLDEntity.class, versionTable);
                }
                endnow = android.os.SystemClock.uptimeMillis();
                Log.d("MYTAG", "Excution time: " + (endnow - startnow) + " ms");
                return true;
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(aBoolean -> result(aBoolean), throwable -> error(throwable));
    }

    private void result(Boolean result) {
        Log.d(TAG, "result() called with: " + "result = [" + result + "]");
        if (result){
            Log.e(TAG,"saved");
        }else{
            Log.e(TAG, "NOT saved");
        }
    }


    private void error(Throwable e){
        endnow = android.os.SystemClock.uptimeMillis();
        Log.d("MYTAG", "Excution time: "+(endnow-startnow)+" ms");
        Log.d(TAG, "error() called with: " + "e = [" + e + "]");
        if (e != null){
            e.printStackTrace();
        }
    }

    private void testActiveRecordWithObservablesChain(){
        for(int i=0;i<100;i++) {
            AndroidVersion lollyPop = new AndroidVersion(21, "LollyPop");
            AndroidVersion.save(ActiveRecordActivity.this, lollyPop);
            AndroidVersion marshmallow = new AndroidVersion(22, "Marshmallow");
            AndroidVersion.save(ActiveRecordActivity.this, marshmallow);
        }

        //TODO: ok, salva
        Observable.defer(() -> Observable.just(AndroidVersion.findById(this, 1)))
                .flatMap(androidVersion -> androidVersion.saveRX(ActiveRecordActivity.this))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(aBoolean -> result(aBoolean), throwable -> error(throwable));


        //TODO: ok, emite errores de salvado
        Observable.defer(() -> Observable.just(AndroidVersion.findById(this, 2)))
                .flatMap(androidVersion -> androidVersion.saveWithExRX())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(aBoolean -> result(aBoolean), throwable -> error(throwable));


        //TODO: ok, no peta con null
        Observable.defer(() -> Observable.just(AndroidVersion.findById(this, 150)))
                .flatMap(androidVersion -> androidVersion.saveWithExRX())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(aBoolean -> result(aBoolean), throwable -> error(throwable));
    }

    private void testTableWithObservableChain(){
        VersionOLDEntity newVersionTable = new VersionOLDEntity(1, "GingerBread");
        VersionOLDEntity.trySave(VersionOLDEntity.class, newVersionTable);

        //Fail example
        VersionOLDEntity.findByIdRX(150)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(versionTable -> finded(versionTable), throwable -> notFinded(throwable));


        VersionOLDEntity newVersionTable2 = new VersionOLDEntity(2, "CupCake");
        VersionOLDEntity.saveRX(newVersionTable2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(aBoolean -> result(aBoolean), throwable -> error(throwable));


        //Find ok
        VersionOLDEntity.findByIdRX(1)
                .flatMap(versionTable -> VersionOLDEntity.saveRX(versionTable))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(aBoolean -> result(aBoolean), throwable -> notFinded(throwable));


        //Version with mapper
        VersionOLDEntity.findByIdRX(1)
                .flatMap(versionTable -> VersionToAndroidMapper.map(versionTable))
                .flatMap(androidTable -> AndroidOLDEntity.saveRX(androidTable))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(aBoolean -> result(aBoolean), throwable -> notFinded(throwable));
    }

    //TODO: crear objecto user antes para pasarselo al mapper que devuelve un observable con un user

    private void finded(VersionOLDEntity versionTable){
        Log.d(TAG, "finded() called with: " + "versionTable = [" + versionTable + "]");
        Log.d(TAG, versionTable.toString());
    }

    private void notFinded(Throwable throwable){
        Log.d(TAG, "notFinded() called with: " + "throwable = [" + throwable + "]");
        Log.d(TAG, throwable.getMessage());
    }
}
