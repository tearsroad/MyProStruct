package com.esyto.myprostruct.ui.activity;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.esyto.myprostruct.R;
import com.esyto.myprostruct.base.BaseActivity;
import com.esyto.myprostruct.bean.request.LoginItem;
import com.esyto.myprostruct.contract.LoginContract;
import com.esyto.myprostruct.model.LoginModel;
import com.esyto.myprostruct.presenter.LoginPresenter;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {

    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.tv_login_msg)
    TextView tvLoginMsg;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        String username = "18030024119";
        String pwd = "123456";
        LoginItem item = new LoginItem();
        item.account = username;
        item.password = pwd;
//        LoginItem.Account ac = item.new Account();
//        ac.account = username;
//        ac.password = pwd;
//        item.request_text = ac;
        mPresenter.login(item,this);
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMsg(String msg) {
        tvLoginMsg.setText(msg);
    }


}
