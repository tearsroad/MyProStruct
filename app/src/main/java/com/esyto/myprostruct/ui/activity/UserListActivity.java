package com.esyto.myprostruct.ui.activity;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.esyto.myprostruct.App;
import com.esyto.myprostruct.R;
import com.esyto.myprostruct.base.BaseActivity;
import com.esyto.myprostruct.bean.request.UserListRq;
import com.esyto.myprostruct.contract.UserListContract;
import com.esyto.myprostruct.model.UserListModel;
import com.esyto.myprostruct.presenter.UserListPresenter;
import com.esyto.myprostruct.ui.layout.TRecyclerView;
import com.esyto.myprostruct.ui.viewHolder.UserItemVH;

import butterknife.Bind;
import butterknife.OnClick;

public class UserListActivity extends BaseActivity<UserListPresenter, UserListModel> implements UserListContract.View {

    @Bind(R.id.btn_list)
    Button btnList;
    @Bind(R.id.lv_user)
    ListView lvUser;
    @Bind(R.id.tv_msg)
    TextView tvMsg;
    @Bind(R.id.trv_user)
    TRecyclerView trvUser;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_list;
    }

    @Override
    public void initView() {
        trvUser.setView(UserItemVH.class).fetch();
    }


    @Override
    public void getListSuccess() {

    }

    @Override
    public void showMsg(String msg) {
        tvMsg.setText(msg);
    }


    @OnClick(R.id.btn_list)
    public void onClick() {
        UserListRq rq = new UserListRq();
        rq.token = App.mDataCache.token;
        rq.shopCode="";
//        rq.request_text.shopCode = "";
        mPresenter.getUserList(rq, this);
    }

}
