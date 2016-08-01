package com.esyto.myprostruct.ui.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.esyto.myprostruct.R;
import com.esyto.myprostruct.base.BaseViewHolder;
import com.esyto.myprostruct.bean.UserListRp;
import com.esyto.myprostruct.util.ImageUtil;

/**
 * Created by lhxez on 2016/7/29.
 */

public class UserItemVH  extends BaseViewHolder<UserListRp>{
    TextView tv_content;
    ImageView im_user;

    public UserItemVH(View v) {
        super(v);
    }

    @Override
    public int getType() {
        return R.layout.list_item_user;
    }

    @Override
    public void onBindViewHolder(View view, UserListRp obj) {
        tv_content.setText(obj.username);
        ImageUtil.loadRoundImg(im_user,obj.face);
    }
}
