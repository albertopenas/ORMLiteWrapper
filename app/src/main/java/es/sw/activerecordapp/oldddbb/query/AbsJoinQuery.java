package es.sw.activerecordapp.oldddbb.query;

import com.j256.ormlite.dao.Dao;

import es.sw.activerecordapp.database.core.DaoManager;
import es.sw.activerecordapp.database.query.abs.Query;

/**
 * Created by alberto on 12/10/15.
 */
public abstract class AbsJoinQuery<EntityA,EntityB,Result> implements Query<Result> {

    private Class aClass;
    private Class bClass;

    public AbsJoinQuery(Class aClass, Class bClass) {
        this.aClass = aClass;
        this.bClass = bClass;
    }

    @Override
    public Result run() {
        Dao<EntityA, Integer> daoA = (Dao<EntityA, Integer>) DaoManager.getDao(aClass);
        Dao<EntityB, Integer> daoB = (Dao<EntityB, Integer>) DaoManager.getDao(bClass);
        return query(daoA, daoB);
    }

    protected abstract Result query(Dao<EntityA, Integer>daoA, Dao<EntityB, Integer>daoB);
}
