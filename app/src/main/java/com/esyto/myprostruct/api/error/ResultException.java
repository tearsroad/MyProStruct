package com.esyto.myprostruct.api.error;

/**
 * Created by lhxez on 2016/7/18.
 */

public class ResultException extends RuntimeException {

    private int errCode = 0;

    public ResultException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }
}