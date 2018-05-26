package com.example.cynthia.imageload.CacheStrategy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.example.cynthia.imageload.CompressStrategy.CompressStrategy;
import com.example.cynthia.imageload.Utils.Encryption;
import com.example.cynthia.imageload.Utils.MD5Encryption;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 实现磁盘缓存
 */

public class DiskCacheStrategy implements CacheStrategy {
    private MD5Encryption md5Encryption = new MD5Encryption();
    private String path;

    private DiskCacheStrategy() {

    }

    public static DiskCacheStrategy getInstance() {
        return DiskHolder.instance;
    }

    private static class DiskHolder {
        private static final DiskCacheStrategy instance = new DiskCacheStrategy();
    }

    @Override
    public void putImage(String address, Bitmap bitmap, CompressStrategy compressStrategy, CompressStrategy.Parameters parameters) {
        FileOutputStream outputStream = null;
        try {
            File diskCache = new File(path + File.separator + "bitmap");
            if (!diskCache.exists())
                diskCache.mkdir();
            outputStream = new FileOutputStream(diskCache+ md5Encryption.encrypt(address+parameters.quality));
            compressStrategy.compress(bitmap, parameters).   //压缩
                    compress(Bitmap.CompressFormat.PNG, 100, outputStream);   //保存
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Bitmap getImage(String address, CompressStrategy.Parameters parameters) {
        return BitmapFactory.decodeFile(path + md5Encryption.encrypt(address+parameters.quality));
    }

    /**
     * 自定义储存路径
     *
     * @param path 自定义路径地址
     */
    public void setPath(String path) {
        this.path = path.endsWith("/") ? path : path + "/";
    }


}
