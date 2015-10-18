package es.sw.activerecordapp.samples;

import es.sw.activerecordapp.database.query.Where;
import es.sw.activerecordapp.model.VersionOLDEntity;

/**
 * Created by alberto on 18/10/15.
 */
public class VersionEntityWhere extends Where<VersionOLDEntity> {

    public VersionEntityWhere(Class aClass, String columnName, Object value) {
        super(aClass, columnName, value);
    }
}
