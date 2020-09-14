package com.dimeno.commons.storage;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.dimeno.commons.utils.AppUtils;

import java.lang.ref.WeakReference;
import java.util.Set;

/**
 * shared preference helper
 * Created by wangzhen on 2020/9/14.
 */
public class SPHelper {
    private static WeakReference<SPHelper> mCache;
    private SharedPreferences mInstance;
    private SharedPreferences.Editor mEditor;

    private SPHelper() {
        if (AppUtils.getContext() != null) {
            mInstance = AppUtils.getContext().getSharedPreferences(AppUtils.getAppName(), Activity.MODE_PRIVATE);
        }
    }

    private SPHelper(String spName) {
        if (AppUtils.getContext() != null) {
            mInstance = AppUtils.getContext().getSharedPreferences(spName, Activity.MODE_PRIVATE);
        }
    }

    public static SPHelper get() {
        SPHelper spHelper;
        if (mCache != null) {
            spHelper = mCache.get();
            if (spHelper != null) {
                return spHelper;
            }
        }
        spHelper = new SPHelper();
        mCache = new WeakReference<>(spHelper);
        return spHelper;
    }

    public static SPHelper get(String spName) {
        return new SPHelper(spName);
    }

    public SharedPreferences.Editor getSharedPreferencesEditor() {
        return mInstance.edit();
    }

    public boolean hasKey(String key) {
        if (mInstance != null) {
            return mInstance.contains(key);
        }
        return false;
    }

    public <T> SPHelper put(String key, T value) {
        if (mEditor == null) {
            mEditor = getSharedPreferencesEditor();
        }
        if (value instanceof Integer) {
            mEditor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            mEditor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            mEditor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            mEditor.putLong(key, (Long) value);
        } else if (value instanceof String) {
            mEditor.putString(key, (String) value);
        } else if (value instanceof Set) {
            mEditor.putStringSet(key, (Set<String>) value);
        } else if (value != null) {
            Log.e(SPHelper.class.getName(), value.getClass() + " unsupported type");
        }

        return this;
    }

    public SPHelper commit() {
        commit(true);
        return this;
    }

    public boolean commit(boolean isAsync) {
        boolean result = false;
        if (mEditor != null) {
            if (isAsync) {
                mEditor.apply();
            } else {
                result = mEditor.commit();
            }
            mEditor = null;
        }
        return result;
    }

    public String get(String key, String defValue) {
        return mInstance.getString(key, defValue);
    }

    public long get(String key, long defValue) {
        return mInstance.getLong(key, defValue);
    }

    public int get(String key, int defValue) {
        return mInstance.getInt(key, defValue);
    }

    public float get(String key, float defValue) {
        return mInstance.getFloat(key, defValue);
    }

    public boolean get(String key, boolean defValue) {
        return mInstance.getBoolean(key, defValue);
    }

    public Set<String> get(String key, Set<String> defValue) {
        return mInstance.getStringSet(key, defValue);
    }

    /**
     * clear all data
     *
     * @return result
     */
    public boolean clear() {
        if (mEditor == null) {
            mEditor = getSharedPreferencesEditor();
        }
        return mEditor.clear().commit();
    }
}
