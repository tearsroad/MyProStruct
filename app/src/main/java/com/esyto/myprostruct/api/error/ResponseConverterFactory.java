package com.esyto.myprostruct.api.error;


import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by lhxez on 2016/7/18.
 */

public class ResponseConverterFactory extends Converter.Factory{
    public static ResponseConverterFactory create() {
        return create(new Gson());
    }

    public static ResponseConverterFactory create(Gson gson) {
        return new ResponseConverterFactory(gson);

    }

    private final Gson gson;

    private ResponseConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson内容为空");
        this.gson = gson;
    }


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {

        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new JsonResponseBodyConverter<>(gson, adapter); //响应
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {

        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new JsonRequestBodyConverter<>(gson, adapter); //请求
    }

}
