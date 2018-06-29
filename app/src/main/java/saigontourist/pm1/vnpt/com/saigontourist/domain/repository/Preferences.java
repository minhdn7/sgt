package saigontourist.pm1.vnpt.com.saigontourist.domain.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashSet;

public class Preferences {

    private Context context;

    public Preferences(Context context) {
        this.context = context;
    }

    public int getInt(String key) {
        return getDefaultSharedPreferences().getInt(key, 0);
    }

    public int getInt(String key, int defValue) {
        return getDefaultSharedPreferences().getInt(key, defValue);
    }

    public void putInt(String key, int value) {
        getDefaultSharedPreferences().edit().putInt(key, value).apply();
    }

    public String getString(String key, String defValue) {
        return getDefaultSharedPreferences().getString(key, defValue);
    }

    public void putString(String key, String value) {
        getDefaultSharedPreferences().edit().putString(key, value).apply();
    }

    public long getLong(String key, long defValue) {
        return getDefaultSharedPreferences().getLong(key, defValue);
    }

    public void putLong(String key, long value) {
        getDefaultSharedPreferences().edit().putLong(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return getDefaultSharedPreferences().getBoolean(key, defValue);
    }

    public void putBoolean(String key, boolean value) {
        getDefaultSharedPreferences().edit().putBoolean(key, value).apply();
    }

    public HashSet<String> getHashSet(String key) {
        return (HashSet<String>) getDefaultSharedPreferences().getStringSet(key, new HashSet<String>(0));
    }

    public void putHashSet(String key, HashSet<String> values) {
        getDefaultSharedPreferences().edit().putStringSet(key, values).apply();
    }

    public void remove(String key) {
        getDefaultSharedPreferences().edit().remove(key).apply();
    }
    public void removeAllValue() {
        getDefaultSharedPreferences().edit().clear().apply();
    }

    public SharedPreferences getDefaultSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}