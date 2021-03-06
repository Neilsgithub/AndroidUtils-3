package com.xiaocoder.android_ptr;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;


import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.R;

/**
 * @author xiaocoder on 2015/10/9
 * @email fengjingyu@foxmail.com
 * @description 封装了上下拉 ， 分页 ，无数据背景
 * <p/>
 * xml可配置autorefresh属性
 */
public class XCGridRefreshLayout extends XCRefreshLayout {

    public XCGridRefreshLayout(Context context) {
        super(context);
    }

    public XCGridRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XCGridRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void inflaterLayout(LayoutInflater mInflater) {
        mInflater.inflate(R.layout.xc_l_view_grid_refresh, this, true);
    }

    @Override
    public void initHeadStyle() {
        mPtrClassicHeader = new PtrClassicDefaultHeader(getContext());
        mPtrRefreshLayout.setHeaderView((PtrClassicDefaultHeader) mPtrClassicHeader);
        mPtrRefreshLayout.addPtrUIHandler(mPtrClassicHeader);
    }

    @Override
    public void initXCRefreshLayoutParams() {
        super.initXCRefreshLayoutParams();
        // mPtrRefreshLayout.setEnabledNextPtrAtOnce(true);
    }
}
