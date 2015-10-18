package es.sw.activerecordapp.oldddbb.query;

import com.j256.ormlite.dao.Dao;

import es.sw.activerecordapp.database.core.DaoManager;
import es.sw.activerecordapp.database.query.abs.Query;

/**
 * Created by alberto on 12/10/15.
 */
public abstract class AbsQuery<J,T> implements Query<T> {

    private Class aClass;

    public AbsQuery(Class aClass) {
        this.aClass = aClass;
    }

    @Override
    public T run() {
        Dao<J, Integer> dao = (Dao<J, Integer>) DaoManager.getDao(aClass);
        return query(dao);
    }

    protected abstract T query(Dao<J, Integer>dao);
}
