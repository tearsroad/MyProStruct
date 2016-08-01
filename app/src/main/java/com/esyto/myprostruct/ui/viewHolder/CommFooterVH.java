package com.esyto.myprostruct.ui.viewHolder;


import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.esyto.myprostruct.R;
import com.esyto.myprostruct.base.BaseViewHolder;


public class CommFooterVH extends BaseViewHolder<Object> {
    public ProgressBar progressbar;
    public TextView tv_state;
    public static final int LAYOUT_TYPE = R.layout.list_footer_view;

    public CommFooterVH(View view) {
        super(view);
    }

    @Override
    public int getType() {
        return LAYOUT_TYPE;
    }

    @Override
    public void onBindViewHolder(View view, Object o) {
        boolean isHasMore = (null == o ? false : true);
        progressbar.setVisibility(isHasMore ? View.VISIBLE : View.GONE);
        tv_state.setText(isHasMore ? "正在加载" : "已经到底");
    }
}