package com.esyto.myprostruct.api.progress;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

/**
 * Created by lhxez on 2016/7/18.
 */

public class ProgressDialogHandler extends Handler {

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private ProgressDialog pd;

    private Context context;
    private boolean cancelable = true;
    private String mProgressMsg="请稍候...";
    private ProgressCancelListener mProgressCancelListener;


    public ProgressDialogHandler(Context context, ProgressCancelListener mProgressCancelListener) {
        super();
        this.context = context;
        this.mProgressCancelListener = mProgressCancelListener;
    }

    private void initProgressDialog(){
        if (pd == null) {
            pd = new ProgressDialog(context);
            pd.setCancelable(cancelable);
            pd.setCanceledOnTouchOutside(cancelable);
            pd.setMessage(mProgressMsg);
            if (cancelable) {
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        mProgressCancelListener.onCancelProgress();
                    }
                });
            }

            if (!pd.isShowing()) {
                pd.show();
            }
        }
    }

    private void dismissProgressDialog(){
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }



    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }



    public void setmProgressMsg(String mProgressMsg) {
        this.mProgressMsg = mProgressMsg;
    }
}