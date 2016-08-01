package com.esyto.myprostruct.api.error;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by lhxez on 2016/7/26.
 */

public class StringConverter implements JsonSerializer<String>,
        JsonDeserializer<String> {
    // 对象转为Json时调用,实现JsonSerializer<PackageState>接口
    public JsonElement serialize(String src, Type typeOfSrc,
                                 JsonSerializationContext context) {
        if (src == null) {
            return new JsonPrimitive("");
        } else {
            return new JsonPrimitive(src.toString());
        }
    }
    // json转为对象时调用,实现JsonDeserializer<PackageState>接口
    public String deserialize(JsonElement json, Type typeOfT,
                              JsonDeserializationContext context)
            throws JsonParseException {
        String value = json.getAsJsonPrimitive().getAsString();
        if(value==null) value="";
        return value;
    }
}
