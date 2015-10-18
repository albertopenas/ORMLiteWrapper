package es.sw.activerecordapp.oldddbb.core;

import es.sw.activerecordapp.database.exception.NotFoundException;
import es.sw.activerecordapp.oldddbb.model.OLDEntity;

/**
 * Created by alberto on 1/10/15.
 */
public class Select<T extends OLDEntity> {


    private Class clazz;


    public Select(){}


    public Select<T>from(Class<? extends OLDEntity> clazz){
        this.clazz = clazz;
        return this;
    }


    public T findById(int id) throws NotFoundException{
        return T.tryFindById(clazz, id);
    }
}
