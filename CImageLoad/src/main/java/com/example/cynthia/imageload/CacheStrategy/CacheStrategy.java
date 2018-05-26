package com.example.cynthia.imageload.CacheStrategy;

import android.graphics.Bitmap;

import com.example.cynthia.imageload.CompressStrategy.CompressStrategy;

/**
 * 图片缓存实现类接口
 */

public interface CacheStrategy {
    /**
     * 将图片置入缓存中
     * @param address 图片获得地址
     * @param bitmap 图片本身
     */
    void putImage(String address, Bitmap bitmap, CompressStrategy compressStrategy,
                  CompressStrategy.Parameters parameters);

    /**
     * 将图片从缓存之中获取
     * @param address 图片地址
     * @return 图片，无返回null
     */
    Bitmap getImage(String address, CompressStrategy.Parameters parameters);
}
