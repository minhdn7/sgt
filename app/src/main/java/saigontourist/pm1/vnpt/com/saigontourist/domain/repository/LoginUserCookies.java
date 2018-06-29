package saigontourist.pm1.vnpt.com.saigontourist.domain.repository;

import java.util.HashSet;

public class LoginUserCookies {

    public static final String PREF_KEY_COOKIES = "jp.drjoy.app.COOKIES";

    private Preferences preferences;

    public LoginUserCookies(Preferences preferences) {
        this.preferences = preferences;
    }

    public HashSet<String> get() {
        return preferences.getHashSet(PREF_KEY_COOKIES);
    }

    public void put(HashSet<String> cookies) {
        preferences.putHashSet(PREF_KEY_COOKIES, cookies);
    }

    public void remove() {
        preferences.remove(PREF_KEY_COOKIES);
    }

}