package com.example.cynthia.imageload.CompressStrategy;

import android.graphics.Bitmap;

/**
 * 无损压缩策略
 */

public class LosslessCompressStrategy extends CompressStrategy {

//  静态内部类单例模式
    private LosslessCompressStrategy(){}

    public static LosslessCompressStrategy getInstance(){
        return LosslessHolder.instance;
    }

    private static class LosslessHolder {
        private static final LosslessCompressStrategy instance = new LosslessCompressStrategy();
    }

    @Override
    public Bitmap compress(Bitmap bitmap, Parameters parameter) {
        return change(bitmap, parameter.height, parameter.width);
    }

    /**
     * 压缩操作
     *
     * @param bitmap 待处理图片
     * @param height 压缩参数中的高度
     * @param width  压缩参数中的宽度
     * @return 压缩后图片
     */

    private Bitmap change(Bitmap bitmap, int height, int width) {
        int tempWidth = bitmap.getWidth();
        int tempHeight = bitmap.getHeight();
//      检查原图片是否满足压缩要求
//      若height/width默认，则不进行压缩（同不选择压缩模式
        if ((tempHeight <= height && tempWidth <= width) || height < 0 || width < 0) {
            return bitmap;
        }
//      计算宽高比例
        float tempRatio = 1f * tempHeight / tempWidth;
        float setRatio = height / width;
        if (tempRatio > setRatio) {
            height = (int) tempRatio*width;
        }else if (tempRatio<setRatio){
            width = (int) (height/tempRatio);
        }
        return Bitmap.createScaledBitmap(bitmap,width,height,true);
    }
}
