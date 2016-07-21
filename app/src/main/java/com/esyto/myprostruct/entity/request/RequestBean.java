package com.esyto.myprostruct.entity.request;

import com.esyto.myprostruct.base.util.LogUtil;
import com.google.gson.Gson;

/**
 * Created by lhxez on 2016/7/21.
 */

public class RequestBean<T>{
    public T request_text;
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("---请求start--\n");
        sb.append(new Gson().toJson(this));
        sb.append("\n----请求end---");
        LogUtil.w(sb.toString());
        return sb.toString();
    }
}
