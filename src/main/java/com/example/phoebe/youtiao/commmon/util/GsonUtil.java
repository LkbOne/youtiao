package com.example.phoebe.youtiao.commmon.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;

public class GsonUtil {
    private static Gson gson = (new GsonBuilder()).disableHtmlEscaping().create();
    private static Gson gsonDealingNull = new GsonBuilder().serializeNulls().create();

    public GsonUtil() {
    }

    public static Gson getGson() {
        return gson;
    }
    
    public static String toJson(Object object){
        return gson.toJson(object);
    }
    
    public static <T> T fromJson(String json, Type type){
        return gson.fromJson(json, type);
    }
    
    public static JsonElement parseToJsonObject(String obj) {
        JsonParser parser = new JsonParser();
        return parser.parse(obj).getAsJsonObject();
    }

    public static String toJsonSerializingNull(Object obj) {
        return gsonDealingNull.toJson(obj);
    }

    public static <T> T fromJsonSerializingNull(String json, Class<T> tClass) {
        return gsonDealingNull.fromJson(json, tClass);
    }

    public static <T> T fromJsonSerializingNull(String json, Type tType) {
        return gsonDealingNull.fromJson(json, tType);
    }
}
