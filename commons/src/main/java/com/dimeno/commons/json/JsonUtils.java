package com.dimeno.commons.json;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * json util
 * Created by wangzhen on 2020/8/19.
 */
public class JsonUtils {

    static private Gson gson = new Gson();

    public static <T> T parseObject(String jsonString, Class<T> cls) {
        return gson.fromJson(jsonString, cls);
    }

    public static <T> T parseObject(String jsonString, Type typeOfT) {
        return gson.fromJson(jsonString, typeOfT);
    }

    public static <T> T parseObject(JsonElement json, Class<T> cls) {
        return gson.fromJson(json, cls);
    }

    public static <T> T parseObject(String jsonString, Class<T> cls, Class<?>... generics) {
        return gson.fromJson(jsonString, type(cls, generics));
    }

    public static <T> List<T> parseArray(String jsonString, Class<T> generic) {
        return gson.fromJson(jsonString, type(List.class, generic));
    }

    public static String toJsonString(Object object) {
        return gson.toJson(object);
    }

    private static <T> ParameterizedType type(final Class<T> raw, final Type... args) {
        return new ParameterizedType() {

            @NonNull
            public Type getRawType() {
                return raw;
            }

            @NonNull
            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}