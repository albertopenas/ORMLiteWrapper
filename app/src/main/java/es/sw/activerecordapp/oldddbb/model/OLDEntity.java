package es.sw.activerecordapp.oldddbb.model;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import es.sw.activerecordapp.database.core.DaoManager;
import es.sw.activerecordapp.database.exception.NotFoundException;
import es.sw.activerecordapp.rx.RxHelper;
import rx.Observable;

import static android.util.Log.e;

/**
 * Created by alberto on 27/9/15.
 */
public class OLDEntity extends Object{


    private static final String TAG = OLDEntity.class.getSimpleName();


    private static<T extends OLDEntity>Dao<T,Integer> getDaoFor(Class clazz) {
        DaoManager manager = new DaoManager();
        Dao<T, Integer> dao = null;//manager.getDaoFor(clazz);
        return dao;
    }

    /**
     * Helper for query if a Dao.CreateOrUpdateStatus was created or updated
     * @param status
     * @return
     */
    private static boolean isCreatedOrUpdated(Dao.CreateOrUpdateStatus status){
        if (status == null) {
            return false;
        }
        if (status.isCreated() || status.isUpdated()) {
            return true;
        }
        return false;
    }


    /**
     * Save
     * @param table
     * @param t
     * @param <T>
     * @return true if saved
     * @throws SQLException
     */
    public static<T extends OLDEntity> boolean save(Class table, T t) throws SQLException{
        boolean success = false;
        Dao<T,Integer>dao = getDaoFor(table);
        success = isCreatedOrUpdated(dao.createOrUpdate(t));
        return success;
    }


    /**
     * Save
     * @param table
     * @param t
     * @param <T>
     * @return true if saved
     */
    public static<T extends OLDEntity> boolean trySave(Class table,T t){
        try {
            return save(table, t);
        } catch (SQLException e) {
            e(TAG, "sql", e);
        } catch (OutOfMemoryError e){
            e(TAG, "out of memory", e);
        }catch (Exception e){
            e(TAG, "exception", e);
        }
        return false;
    }


    /**
     * Save RX
     * @param table
     * @param t
     * @param <T>
     * @return
     */
    public static<T extends OLDEntity> Observable<Boolean> saveRX(Class table,  T t) {
        return RxHelper.createObservable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return save(table, t);
            }
        });
    }


    /**
     * Find by id
     * @param table
     * @param id
     * @param <T>
     * @return
     * @throws SQLException
     */
    public static<T extends OLDEntity> T findById(Class table, Integer id) throws SQLException, NotFoundException{
        Dao<T,Integer>dao = getDaoFor(table);
        T t = dao.queryForId(id);
        if (t == null){
            throw new NotFoundException("find by not found any " + table.getSimpleName() + " with id: " + id);
        }
        return t;
    }


    /**
     * Find by id
     * @param table
     * @param id
     * @param <T>
     * @return
     * @throws NotFoundException
     */
    public static<T extends OLDEntity> T tryFindById(Class table, Integer id) throws NotFoundException{
        try {
            return findById(table, id);
        } catch (SQLException e) {
            e(TAG, "sql", e);
        } catch (OutOfMemoryError e){
            e(TAG, "out of memory", e);
        }catch (Exception e){
            e(TAG, "exception", e);
        }
        throw new NotFoundException("tryFindById not found any " + table.getSimpleName() + " with id: " + id);
    }


    /**
     * Find by id RX
     * @param table
     * @param id
     * @param <T>
     * @return
     */
    public static<T extends OLDEntity> Observable<T> findByIdRX(Class table, Integer id) {
        return RxHelper.createObservable(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return findById(table, id);
            }
        });
    }


    /**
     * Save a collection
     * @param table
     * @param list
     * @param <T>
     * @return
     * @throws Exception
     */
    public static<T extends OLDEntity> boolean save(Class table, final List<T> list) throws Exception {
        if (list.isEmpty()) {
            return false;
        }
        Dao<T, Integer> dao = getDaoFor(table);
        return dao.callBatchTasks(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                for (T t : list) {
                    boolean createdOrUpdated = OLDEntity.isCreatedOrUpdated(dao.createOrUpdate(t));
                    if (!createdOrUpdated) {
                        return false;
                    }
                }
                return true;
            }
        });
    }


    /**
     * Try save
     * @param table
     * @param list
     * @param <T>
     * @return
     * @throws Exception
     */
    public static<T extends OLDEntity> boolean trySave(Class table, final List<T> list) {
        try {
            return save(table,list);
        } catch (SQLException e) {
            e(TAG, "sql", e);
        } catch (OutOfMemoryError e){
            e(TAG, "out of memory", e);
        }catch (Exception e){
            e(TAG, "exception", e);
        }
        return false;
    }

    /**
     * FindAll
     * @param table
     * @param <T>
     * @return
     * @throws NotFoundException
     */
    public static<T extends OLDEntity> List<T> tryFindAll(Class table) throws NotFoundException {
        try {
            return findAll(table);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        throw new NotFoundException("tryFindById not found any item for " + table.getSimpleName());
    }
    /**
     * Save RX
     * @param table
     * @param list
     * @param <T>
     * @return
     */
    public static<T extends OLDEntity> Observable<Boolean> saveRX(Class table,  List<T> list) {
        return RxHelper.createObservable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return save(table, list);
            }
        });
    }


    /**
     * FindAll
     * @param table
     * @param <T>
     * @return
     * @throws SQLException
     */
    public static<T extends OLDEntity> List<T> findAll(Class table) throws SQLException, NotFoundException {
        Dao<T, Integer> dao = getDaoFor(table);
        List<T> list = dao.queryForAll();
        if (list == null || list.isEmpty()){
            throw new NotFoundException("tryFindById not found any item for " + table.getSimpleName());
        }
        return list;
    }

    //TODO: orderBy
    //TODO: delete
    //TODO: deleteAll
    //TODO: deleteAll(list)
    //TODO: deleteByField
    //TODO: where
    //TODO: foreignWhere
}
