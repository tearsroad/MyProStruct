package com.esyto.myprostruct.api.error;

/**
 * 错误信息提醒类
 * Created by lhxez on 2016/7/21.
 */

public class ErrorMsg {
    public static final int UNKNOWN = 1000;
    public static final int PARSE_ERROR = 1001;
    public static final int TIMEOUT_ERROR = 1002;
    public static final int NONET_ERROR = 1003;

    public static String getErrorMsg(int code){
        String msg = "请求服务器，未知错误。";
        switch (code){
            case 400:
                msg = "服务器不理解请求的语法。";
                break;
            case 401:
                msg = "连接服务器未授权。";
                break;
            case 403:
                msg = "服务器拒绝请求。";
                break;
            case 404:
                msg = "服务器找不到请求的网页。";
                break;
            case 410:
                msg = "请求的资源被永久删除。";
                break;
            case 500:
                msg = "服务器遇到错误，无法完成请求。";
                break;
            case 502:
                msg = "网关出错。";
                break;
            case 503:
                msg = "服务器目前无法使用。";
                break;
            case 504:
                msg = "网关超时。";
                break;
            case PARSE_ERROR:
                msg = "JSON解析失败";
                break;
        }
        return msg;
    }
}
