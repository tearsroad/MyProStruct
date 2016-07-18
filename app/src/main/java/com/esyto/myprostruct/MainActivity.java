package com.esyto.myprostruct;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ec2.tools.http.BaseTast;
import com.esyto.myprostruct.base.BaseActivity;
import com.esyto.myprostruct.login.LoginActivity;
import com.esyto.myprostruct.ui.UserListActivity;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.btn_list)
    Button btnList;
    private String TAG = "TEST";

    @Bind(R.id.tv_test)
    TextView tvTest;
    @Bind(R.id.btn_test)
    Button btnTest;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        tvTest.setText("test for butterKnife");
        Logger.d(TAG, "test 1");
        Logger.i(TAG, "test 1");
        Logger.e(TAG, "test 1");
        Logger.w(TAG, "test 1");
        Logger.v(TAG, "test 1");
    }


    @OnClick(R.id.btn_test)
    public void onClick() {
        String ts = "";
        BaseTast t = new BaseTast();
        ts = t.testForLib();
        Toast.makeText(this, "butterKnife onclick!" + ts, Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, RxTestActivity.class));
    }

    @OnClick(R.id.btn_login)
    public void onLoginClick() {
        startActivity(new Intent(this, LoginActivity.class));
    }



    @OnClick(R.id.btn_list)
    public void onListClick() {
        startActivity(new Intent(this, UserListActivity.class));
    }
}
