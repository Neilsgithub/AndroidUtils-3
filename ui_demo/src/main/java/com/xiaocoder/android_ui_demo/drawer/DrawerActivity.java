package com.xiaocoder.android_ui_demo.drawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.xiaocoder.android_ui_demo.R;

/**
 * @author xiaocoder
 * @email fengjingyu@foxmail.com
 * @description
 */
public class DrawerActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContent();
        initDrawer();

        Button button = (Button) findViewById(R.id.drawer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen()) {
                    closeDrawer();
                } else {
                    openDrawer();
                }
            }
        });
    }

    private void initContent() {
        View content = LayoutInflater.from(this).inflate(R.layout.activity_drawer, null);
        setContentFrame(content);
    }

    public void initDrawer() {
        View drawer = LayoutInflater.from(this).inflate(R.layout.view_drawer, null);
        setDrawerFrame(drawer);
    }
}
