package com.example.cynthia.imageload.CompressStrategy;

import android.graphics.Bitmap;

/**
 * 不进行压缩（不大推荐）
 */

public class NoneCompressStrategy extends CompressStrategy {
    private NoneCompressStrategy(){}

    private static NoneCompressStrategy getInstance(){
        return NoneHolder.instance;
    }

    private static class NoneHolder{
        private static final NoneCompressStrategy instance = new NoneCompressStrategy();
    }

    @Override
    public Bitmap compress(Bitmap bitmap, Parameters parameter) {
        return bitmap;
    }
}
