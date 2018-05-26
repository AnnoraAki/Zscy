package com.example.cynthia.imageload;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.cynthia.imageload.CacheStrategy.BothCacheStrategy;
import com.example.cynthia.imageload.CacheStrategy.CacheStrategy;
import com.example.cynthia.imageload.CacheStrategy.DiskCacheStrategy;
import com.example.cynthia.imageload.CompressStrategy.CompressStrategy;
import com.example.cynthia.imageload.CompressStrategy.LosslessCompressStrategy;

import java.util.Objects;

public class ConfigSetting {
    private Context mContext;
    private String mUrl;
    private CacheStrategy mCacheStrategy;
    private CompressStrategy mCompressStrategy;
    private CompressStrategy.Parameters parameters;
    private Drawable mError;
    private Drawable mWaiting;

    private ConfigSetting(){}

    public Context getmContext() {
        return mContext;
    }

    public String getmUrl() {
        return mUrl;
    }

    public CacheStrategy getmCacheStrategy() {
        return mCacheStrategy;
    }

    public CompressStrategy getmCompressStrategy() {
        return mCompressStrategy;
    }

    public CompressStrategy.Parameters getParameters() {
        return parameters;
    }

    public Drawable getmError() {
        return mError;
    }

    public Drawable getmWaiting() {
        return mWaiting;
    }


    public static class builder{
        private ConfigSetting mConfig = new ConfigSetting();
        private String mPath;

        public builder(@NonNull Context context){
            this.mConfig.mContext = context;
            this.mConfig.parameters = new CompressStrategy.Parameters();
        }

        public builder from(String url){
            mConfig.mUrl = url;
            return this;
        }

        public builder cacheStrategy(CacheStrategy cacheStrategy){
            mConfig.mCacheStrategy = cacheStrategy;
            return this;
        }

        public builder compressStrategy(CompressStrategy compressStrategy){
            mConfig.mCompressStrategy = compressStrategy;
            return this;
        }

        public builder quality(int quality){
            mConfig.parameters.quality = quality;
            return this;
        }

        public builder error(Drawable drawable){
            mConfig.mError = drawable;
            return this;
        }

        public builder error(@DrawableRes int id){
            error(mConfig.mContext.getResources().getDrawable(id));
            return this;
        }

        public builder waiting(Drawable drawable){
            mConfig.mWaiting = drawable;
            return this;
        }

        public builder waiting(@DrawableRes int id){
            waiting(mConfig.mContext.getResources().getDrawable(id));
            return this;
        }

        public builder size(int height,int width){
            mConfig.parameters.height = height;
            mConfig.parameters.width = width;
            return this;
        }



        public builder scaleType (ImageView.ScaleType scaleType){
            mConfig.parameters.scaleType = scaleType;
            return this;

        }

        public ConfigSetting build(){
            checkSetting();
            return mConfig;
        }

        private void checkSetting(){
            CacheStrategy cacheStrategy = mConfig.getmCacheStrategy();
            if (cacheStrategy == null){
                mConfig.mCacheStrategy = BothCacheStrategy.getInstance();
                cacheStrategy = BothCacheStrategy.getInstance();
            }
            CompressStrategy compressStrategy = mConfig.getmCompressStrategy();
            if (compressStrategy == null)
                mConfig.mCompressStrategy = LosslessCompressStrategy.getInstance();
            if (cacheStrategy instanceof DiskCacheStrategy) {
                if (mPath == null)
                    mPath = Objects.requireNonNull(mConfig.mContext.getExternalCacheDir()).getPath();
                ((DiskCacheStrategy) cacheStrategy).setPath(mPath);
            } else if (cacheStrategy instanceof BothCacheStrategy) {
                if (mPath == null)
                    mPath = Objects.requireNonNull(mConfig.mContext.getExternalCacheDir()).getPath();
                ((BothCacheStrategy) cacheStrategy).setPath(mPath);
            }
        }
    }

}
