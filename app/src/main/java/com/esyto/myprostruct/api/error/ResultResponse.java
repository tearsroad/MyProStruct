package com.esyto.myprostruct.api.error;

import com.esyto.myprostruct.base.util.LogUtil;
import com.esyto.myprostruct.entity.MyBaseBean;
import com.google.gson.Gson;

/**
 * Created by lhxez on 2016/7/20.
 */

public class ResultResponse {

    public Data response_text;
    public class Data{
        public int code;
        public String message;
    }
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("该Bean的字符串数据---start--\n");
        sb.append(new Gson().toJson(this));
        sb.append("\n----end---");
        LogUtil.w(sb.toString());
        return sb.toString();
    }
}
