package com.esyto.myprostruct.api.error;

import com.esyto.myprostruct.App;
import com.esyto.myprostruct.bean.request.BaseRequest;
import com.esyto.myprostruct.util.NetWorkUtil;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * Created by lhxez on 2016/7/18.
 */

public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    /**
     * 构造器
     */

    public JsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }


    @Override
    public RequestBody convert(T value) throws IOException {
        if(value instanceof BaseRequest){
            String postBody = gson.toJson(value);
            JSONObject obj = new JSONObject();
            try {
                obj.put("request_text",postBody);
                Logger.w("BaseRequest中传递的json数据：" + obj.toString());
                return RequestBody.create(MEDIA_TYPE, obj.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                throw new ResultException(ErrorMsg.NONET_ERROR
                        , "RequestBody Json请求封装失败！");
            }
        }else {
            Buffer buffer = new Buffer();
            Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
            JsonWriter jsonWriter = gson.newJsonWriter(writer);
            adapter.write(jsonWriter, value);
            jsonWriter.close();
            //加密
//        APIBodyData data = new APIBodyData();
            String postBody = gson.toJson(value); //对象转化成json
            Logger.w("request中传递的json数据：" + postBody);
//        data.setData(XXTEA.Encrypt(value.toString(), HttpConstant.KEY));
//        Log.i("xiaozhang", "转化后的数据：" + postBody);
            if (!NetWorkUtil.isNetConnected(App.getAppContext())) {
                throw new ResultException(ErrorMsg.NONET_ERROR
                        , "无网络连接，请检查网络！");
            } else {
                return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
            }
        }

    }

}