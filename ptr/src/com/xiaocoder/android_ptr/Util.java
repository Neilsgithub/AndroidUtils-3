package com.xiaocoder.android_ptr;

import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author xiaocoder on 2016/7/5 17:20
 * @email fengjingyu@foxmail.com
 * @description
 */
public class Util {


    public static int getScreenHeightPx(Context context) {
        return getScreenSizeByMetric(context)[1];

    }

    public static int getScreenWidthPx(Context context) {
        return getScreenSizeByMetric(context)[0];
    }

    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (scale * dipValue + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕分辨率
     */
    public static int[] getScreenSizeByMetric(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metric = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metric);
        int[] size = new int[3];
        size[0] = metric.widthPixels;
        size[1] = metric.heightPixels;
        size[2] = metric.densityDpi;
        return size;
    }

    public static String getStringFromRaw(Context context, int resId) {
        if (context == null) {
            return null;
        }

        return toStringByInputStream(getInputStreamFromRaw(context, resId));
    }

    public static InputStream getInputStreamFromRaw(Context context, int drawable_id) {
        try {
            return context.getResources().openRawResource(drawable_id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toStringByInputStream(InputStream inputStream) {
        BufferedReader br = null;
        try {
            StringBuilder s = new StringBuilder("");
            InputStreamReader in = new InputStreamReader(inputStream);
            br = new BufferedReader(in);
            String line = null;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 触摸的点是否在一个view内
     */
    public static boolean isTouchInsideView(List<? extends View> views, MotionEvent ev, int extraDistance) {

        if (views == null || views.isEmpty()) {
            return false;
        }

        for (View v : views) {

            int[] sizes = new int[2];
            v.getLocationOnScreen(sizes);

            Rect mRect = new Rect();
            mRect.left = sizes[0] - extraDistance;
            mRect.top = sizes[1] - extraDistance;
            mRect.right = sizes[0] + v.getWidth() + extraDistance;
            mRect.bottom = sizes[1] + v.getHeight() + extraDistance;

            if (mRect.contains((int) ev.getX(), (int) ev.getY())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取origin中最后一个simbol之后的字符串
     *
     * @param origin
     * @param symbol
     * @return
     */
    public static String getAfterLastSimbolString(String origin, String symbol) {
        int index = origin.lastIndexOf(symbol);
        if (index >= 0) {
            if (index + symbol.length() == origin.length()) {
                return "";
            } else {
                return origin.substring(index + symbol.length(), origin.length()).trim();
            }
        }
        return origin;
    }


    /**
     * 获取origin中最后一个simbol之前的字符串
     *
     * @param origin
     * @param symbol
     * @return
     */
    public static String getBeforeLastSimbolString(String origin, String symbol) {
        int position = origin.lastIndexOf(symbol);
        if (position > 0) {
            return origin.substring(0, position).trim();
        } else if (position == 0) {
            return "";
        }
        return origin;
    }

    public static void setGone(boolean isShow, View view) {
        if (isShow) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    public static void setVisible(boolean isShow, View view) {
        if (isShow) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }
}
