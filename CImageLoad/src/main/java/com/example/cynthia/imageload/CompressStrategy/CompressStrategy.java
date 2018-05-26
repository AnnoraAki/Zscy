package com.example.cynthia.imageload.CompressStrategy;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * 图片压缩接口
 */

public abstract class CompressStrategy {
    /**
     * 压缩图片.jpg
     * @param bitmap 待处理图片
     * @param parameter 压缩参数
     * @return 压缩后的图片
     */

    public abstract Bitmap compress(Bitmap bitmap,Parameters parameter);

    /**
     * 设置图片压缩的默认参数
     */

    public static class Parameters{
//      设置宽高
        public int height = -1;
        public int width = -1;
//      设置压缩质量(1-100)
        public int quality = 100;
//      设置压缩比例(小数点)
        public float ratio = 0.5f;
        public ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_CROP;
    }
}
