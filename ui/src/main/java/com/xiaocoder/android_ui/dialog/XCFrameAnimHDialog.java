package com.xiaocoder.android_ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaocoder.android_ui.R;

import java.util.List;

/**
 * @author xiaocoder
 * @email fengjingyu@foxmail.com
 * @description 帧动画的dialog
 */
public class XCFrameAnimHDialog extends Dialog {

    public static int TRAN_STYLE = R.style.trans_dialog;

    /**
     * 如果这里使用getLayoutInflater(),则获取不到双圈的dialog，用LayoutInflater.from可以
     */
    public LayoutInflater dialogInflater;

    public ViewGroup dialogLayout;

    public Context mContext;

    public AnimationDrawable animDrawable;

    int anim_framelist_id;

    List<Integer> imageIdList;
    int timeGap;

    ImageView imageView;
    TextView textView;

    public XCFrameAnimHDialog(Context context, int anim_framelist_id) {
        super(context, TRAN_STYLE);
        dialogInflater = LayoutInflater.from(context);
        mContext = context;

        this.anim_framelist_id = anim_framelist_id;
        initDialog();
    }

    public XCFrameAnimHDialog(Context context, List<Integer> imageIdList, int timeGap) {
        super(context, TRAN_STYLE);
        dialogInflater = LayoutInflater.from(context);
        mContext = context;

        this.imageIdList = imageIdList;
        this.timeGap = timeGap;
        initDialog();
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTextView() {
        return textView;
    }

    public void initDialog() {

        Drawable drawable = getDrawable();

        dialogLayout = (ViewGroup) dialogInflater.inflate(R.layout.xc_l_dialog_animation_h, null);

        textView = (TextView) dialogLayout.findViewById(R.id.xc_id_dialog_anim_h_textview);

        imageView = (ImageView) dialogLayout.findViewById(R.id.xc_id_dialog_anim_h_imageview);

        imageView.setImageDrawable(animDrawable = (AnimationDrawable) drawable);

        setContentView(dialogLayout);
        setWindowLayoutStyleAttr();
    }

    // 可以在onstart()方法中判断 isRunning 与stop
    public Drawable getDrawable() {
        if (imageIdList == null) {
            return mContext.getResources().getDrawable(anim_framelist_id);
        } else {
            return getAnimationDrawable(mContext, imageIdList, timeGap);
        }
    }

    public void setWindowLayoutStyleAttr() {
        dialogLayout.setBackgroundColor(0x00000000);
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.7f;
        lp.dimAmount = 0.3f;
        window.setAttributes(lp);
    }

    @Override
    public void show() {
        super.show();
        if (animDrawable != null) {
            animDrawable.start();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (animDrawable != null) {
            animDrawable.stop();
        }
    }

    public AnimationDrawable getAnimationDrawable(Context context, List<Integer> list, int gap) {
        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.setOneShot(false);// 是否执行一次动画
        Resources resouces = context.getResources();
        for (Integer i : list) {
            Drawable frame = resouces.getDrawable(i);
            animationDrawable.addFrame(frame, gap);
        }
        return animationDrawable;
    }
}



