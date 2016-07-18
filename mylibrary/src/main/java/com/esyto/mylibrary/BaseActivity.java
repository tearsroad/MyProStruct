package com.esyto.mylibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ec2.tools.http.BaseTast;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    //lib下R.id.xxx不再是final类型，不能用注入
//    @Bind(R.id.tv_msg_base)
//    TextView tvMsgBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        LibTools tools = new LibTools();
        tools.setTest(4);
        ButterKnife.bind(this);
        BaseTast test = new BaseTast();
        test.testForLib();
    }


}
