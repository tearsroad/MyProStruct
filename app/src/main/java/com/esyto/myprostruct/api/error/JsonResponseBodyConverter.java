package com.esyto.myprostruct.api.error;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

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
            JSONObject obj = new JSONObject(response);
            Logger.w("服务器数据：" + response);
            if(obj.has("response_text")) {
                ResultResponse resultResponse = mGson.fromJson(response, ResultResponse.class);
                if (resultResponse.response_text.code == 0) {
                    response = obj.getString("response_text");
                    return adapter.fromJson(response);
                } else {
                    throw new ResultException(resultResponse.response_text.code
                            , resultResponse.response_text.message);
                }
            }else{
                return adapter.fromJson(response);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            responseBody.close();
            throw new ResultException(ErrorMsg.PARSE_ERROR
                    , ErrorMsg.getErrorMsg(ErrorMsg.PARSE_ERROR));
        } finally {
            responseBody.close();
        }


    }

}