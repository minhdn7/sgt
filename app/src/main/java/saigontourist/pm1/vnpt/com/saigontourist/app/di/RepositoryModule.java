package saigontourist.pm1.vnpt.com.saigontourist.app.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.AppState;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.LoginUserCookies;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.Preferences;

/**
 * Created by linhl on 4/13/2018.
 */
@Module(complete = false, library = true)
public class RepositoryModule {
    @Provides
    public Preferences providePreferences(Context context) {
        return new Preferences(context);
    }

    @Provides
    public AppState provideAppState(Preferences preferences) {
        return new AppState(preferences);
    }
    @Provides
    public LoginUserCookies provideLoginUserCookies(Preferences preferences) {
        return new LoginUserCookies(preferences);
    }
}
