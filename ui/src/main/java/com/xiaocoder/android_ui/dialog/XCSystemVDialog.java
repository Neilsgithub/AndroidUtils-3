package com.xiaocoder.android_ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.xiaocoder.android_ui.R;


/**
 * @author xiaocoder
 * @email fengjingyu@foxmail.com
 * @description
 */
public class XCSystemVDialog extends Dialog {

    public static int TRAN_STYLE = R.style.trans_dialog;

    /*
     * 如果这里使用getLayoutInflater(),则获取不到双圈的dialog，用LayoutInflater.from可以
     */
    public LayoutInflater dialogInflater;

    public ViewGroup dialogLayout;

    public Context mContext;

    TextView textview;

    public TextView getTextview() {
        return textview;
    }

    public XCSystemVDialog(Context context) {
        super(context, TRAN_STYLE);
        dialogInflater = LayoutInflater.from(context);
        mContext = context;

        initDialog();
    }

    public void initDialog() {
        dialogLayout = (ViewGroup) dialogInflater.inflate(R.layout.xc_l_dialog_system_circle_v, null);
        textview = (TextView) dialogLayout.findViewById(R.id.xc_id_dialog_sys_v_textview);
        setContentView(dialogLayout);
        setWindowLayoutStyleAttr();
    }

    public void setWindowLayoutStyleAttr() {
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.7f;
        lp.dimAmount = 0.3f;
        window.setAttributes(lp);
    }

}



