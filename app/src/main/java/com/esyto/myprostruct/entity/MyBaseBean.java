package com.esyto.myprostruct.entity;

import com.esyto.myprostruct.base.util.LogUtil;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lhxez on 2016/7/14.
 */

public class MyBaseBean <T>{

    public T response_text;
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
