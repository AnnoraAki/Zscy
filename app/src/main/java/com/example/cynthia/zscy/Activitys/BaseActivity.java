package com.example.cynthia.zscy.Activitys;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cynthia.httphelper.HttpHelper;
import com.example.cynthia.zscy.R;
import com.example.cynthia.zscy.Utils.Application;
import com.example.cynthia.zscy.Utils.StatusBarUtils;
import com.example.cynthia.zscy.Utils.ToastUtils;

public class BaseActivity extends AppCompatActivity {

    private TextView title;
    private ImageView left;
    private ImageView right;
    private Toolbar mToolbar;
    private RelativeLayout content;
    public HttpHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        StatusBarUtils.setTransparent(this);
        title = findViewById(R.id.bar_title);
        mToolbar = findViewById(R.id.bar);
        content = findViewById(R.id.layout_content);
        left = findViewById(R.id.bar_left);
        right = findViewById(R.id.bar_right);

        title.setTextSize(18);

        checkProcession();
        setToolbarInvisible();
    }

    public void setTitle(String title){
        this.title.setText(title);
    }

    public void setContentLayout(@LayoutRes int layoutId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(layoutId, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        content.addView(contentView, params);
    }

    public void setToolbarVisible(){
        mToolbar.setVisibility(View.VISIBLE);
    }

    public void setLeft(Drawable drawable){
        left.setImageDrawable(drawable);
    }

    public void setRight(Drawable drawable){
        right.setImageDrawable(drawable);
    }

    public void setToolbarInvisible(){
        mToolbar.setVisibility(View.GONE);
    }

    public void setLeftClick(View.OnClickListener listener){
        left.setOnClickListener(listener);
    }

    public void setRightClick(View.OnClickListener listener){
        right.setOnClickListener(listener);
    }

    protected void checkProcession(){
        if (ContextCompat.checkSelfPermission(Application.getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.
                PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    ToastUtils.showError("未获得相关权限:(");
                }
                break;
            default:
        }
    }

}
