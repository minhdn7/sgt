package saigontourist.pm1.vnpt.com.saigontourist.app;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import dagger.ObjectGraph;
import saigontourist.pm1.vnpt.com.saigontourist.app.di.AppModule;

/**
 * Created by linhl on 4/13/2018.
 */

public class SaiGonTouristApplication extends MultiDexApplication {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        // dagger
        objectGraph = ObjectGraph.create(new AppModule(this));
        objectGraph.inject(this);
    }
    /**
     * @param object {@link Object}
     */
    public void inject(Object object) {
        objectGraph.inject(object);
    }
}
