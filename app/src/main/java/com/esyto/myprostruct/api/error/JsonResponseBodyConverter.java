package com.esyto.myprostruct.api.error;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by lhxez on 2016/7/18.
 */

public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson mGson;//gson对象
    private final TypeAdapter<T> adapter;

    /**
     * 构造器
     */
    public JsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.mGson = gson;
        this.adapter = adapter;
    }

    /**
     * 转换
     *
     * @param responseBody
     * @return
     * @throws IOException
     */
    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        try {
        String response = responseBody.string();

        ResultResponse resultResponse = mGson.fromJson(response, ResultResponse.class);
        Logger.w("服务器数据：" + response);
        Logger.w("resultResponse：" + resultResponse.toString());
        if (resultResponse.code == 0){
            return adapter.fromJson(response);
        }else{
            throw new ResultException(resultResponse.code, resultResponse.message);
        }


        } finally {
            responseBody.close();
        }


    }

}