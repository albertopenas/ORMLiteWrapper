package es.sw.activerecordapp;

import android.app.Application;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import es.sw.activerecordapp.database.core.DatabaseManager;

/**
 * Created by alberto on 27/9/15.
 */
public class AndroidApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        DatabaseHelper helper = OpenHelperManager.getHelper(getApplicationContext(), DatabaseHelper.class);
        DatabaseManager.newInstance(helper);
    }
}
