package com.xiaocoder.android_ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaocoder.android_ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaocoder
 * @email fengjingyu@foxmail.com
 * @description
 */
public class XCMenuDialog extends Dialog {

    public interface OnDialogItemClickListener {
        void onClick(View view, String hint);
    }

    public static int TRAN_STYLE = R.style.trans_dialog;

    /*
     * 如果这里使用getLayoutInflater(),则获取不到双圈的dialog，用LayoutInflater.from可以
     */
    public LayoutInflater dialogInflater;

    public ViewGroup dialogLayout;

    public Context mContext;

    OnDialogItemClickListener listener;

    public void setOnDialogItemClickListener(OnDialogItemClickListener listener) {
        this.listener = listener;
    }

    ListView listview;
    TextView title_textview;

    public XCMenuDialog(Context context) {
        super(context, TRAN_STYLE);
        dialogInflater = LayoutInflater.from(context);
        mContext = context;
        adapter = new ItemsAdapter(mContext, null);
        initDialog();
    }

    public void update(String title, String[] items) {

        if (TextUtils.isEmpty(title)) {
            title_textview.setVisibility(View.GONE);
        } else {
            title_textview.setText(title);
            title_textview.setVisibility(View.VISIBLE);
        }

        adapter.update(getList(items));
        listview.setAdapter(adapter);
    }

    public class ItemsAdapter extends BaseAdapter implements View.OnClickListener {

        public List<String> list;
        public Context context;

        public List<String> getList() {
            if (list == null) {
                return list = new ArrayList<String>();
            }
            return list;
        }

        public void update(List<String> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            if (list != null)
                return list.size();
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (list != null) {
                return list.get(position);
            } else {
                return null;
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        public ItemsAdapter(Context context, List<String> list) {
            this.list = list;
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onClick(view, ((Button) view).getText().toString().trim());
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            String bean = list.get(position);
            ItemHolder holder = null;

            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.xc_l_dialog_item_of_items, null);
                holder = new ItemHolder(convertView);
                holder.item_button.setOnClickListener(this);
                convertView.setTag(holder);
            } else {
                holder = (ItemHolder) convertView.getTag();
            }
            holder.item_button.setText(bean);
            return convertView;
        }

        class ItemHolder {
            Button item_button;

            public ItemHolder(View convertView) {
                item_button = (Button) convertView.findViewById(R.id.item_button);
            }
        }
    }

    public ItemsAdapter adapter;

    public void initDialog() {
        dialogLayout = (ViewGroup) dialogInflater.inflate(R.layout.xc_l_dialog_items, null);
        title_textview = (TextView) dialogLayout.findViewById(R.id.xc_id_dialog_items_title);
        listview = (ListView) dialogLayout.findViewById(R.id.xc_id_dialog_items_listview);
        setContentView(dialogLayout);
        setWindowLayoutStyleAttr();
    }

    public List<String> getList(String[] items) {
        ArrayList<String> list = new ArrayList<>();
        for (String item : items) {
            list.add(item);
        }
        return list;
    }


    public void setWindowLayoutStyleAttr() {
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.95f;
        lp.dimAmount = 0.3f;
        window.setAttributes(lp);
    }


}



