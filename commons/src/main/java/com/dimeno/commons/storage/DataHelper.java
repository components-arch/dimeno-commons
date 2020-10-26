package com.dimeno.commons.storage;

import androidx.annotation.Nullable;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * data helper
 * Created by wangzhen on 2018/3/1.
 */
public class DataHelper {
    private static DataHelper mInstance;
    private final Map<String, SoftReference<Object>> mCache = new HashMap<>();

    public static DataHelper get() {
        if (mInstance == null) {
            synchronized (DataHelper.class) {
                if (mInstance == null) {
                    mInstance = new DataHelper();
                }
            }
        }
        return mInstance;
    }

    public void put(@Nullable String key, @Nullable Object value) {
        mCache.put(key, new SoftReference<>(value));
    }

    @Nullable
    public Object getData(@Nullable String key) {
        SoftReference<?> softReference = mCache.get(key);
        return softReference == null ? null : softReference.get();
    }

    @Nullable
    public Reference<Object> remove(@Nullable String key) {
        return mCache.remove(key);
    }

    public void removeAll() {
        mCache.clear();
    }
}
