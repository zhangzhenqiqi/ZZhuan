package com.example.zzq.zzhuan;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.view.View;
import android.widget.TextView;

public class NavigationBarActivity extends RelativeLayout implements View.OnClickListener {
    private TextView iv_navi_back;
    private TextView tv_navi_title;
    private TextView iv_navi_save;

    public NavigationBarActivity(Context context) {
        super(context);
    }
    public NavigationBarActivity(Context context,AttributeSet attrs)
    {
        super(context,attrs);

        View view  = LayoutInflater.from(context).inflate(R.layout.activity_navigationbar,this,true);

        iv_navi_back =  findViewById(R.id.iv_navi_back);
        iv_navi_back.setOnClickListener(this);

        tv_navi_title = (TextView) findViewById(R.id.tv_navi_title);

        iv_navi_save =  findViewById(R.id.iv_navi_save);
        iv_navi_save.setOnClickListener(this);
    }

    public void setTitle(String title){
        tv_navi_title.setText(title);
    }
    public TextView getIv_navi_back() {
        return iv_navi_back;
    }

    public void setIv_navi_back(TextView iv_navi_back) {
        this.iv_navi_back = iv_navi_back;
    }

    public TextView getTv_navi_title() {
        return tv_navi_title;
    }

    public void setTv_navi_title(TextView tv_navi_title) {
        this.tv_navi_title = tv_navi_title;
    }

    public TextView getIv_navi_save() {
        return iv_navi_save;
    }

    public void setIv_navi_save(TextView iv_navi_save) {
        this.iv_navi_save = iv_navi_save;
    }

    private ClickCallback callback;
    public void setClickCallback(ClickCallback callback)
    {
        this.callback = callback;
    }
    public static interface ClickCallback
    {
        void onBackClick();
        void onRightClick();
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.iv_navi_back)
        {
            callback.onBackClick();
            return;
        }
        if (id == R.id.iv_navi_save)
        {
            callback.onRightClick();
            return;
        }
    }


}
