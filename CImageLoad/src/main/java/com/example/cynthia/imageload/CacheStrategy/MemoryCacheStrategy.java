package com.example.cynthia.imageload.CacheStrategy;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.example.cynthia.imageload.CompressStrategy.CompressStrategy;

public class MemoryCacheStrategy implements CacheStrategy {
    private LruCache<String,Bitmap> mCache;

    private MemoryCacheStrategy(){
        int maxMemory = (int)(Runtime.getRuntime().maxMemory()/1024);
        int cacheSize = maxMemory/8;    //一般设置为最大内存的1/8
        mCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key,Bitmap value){
//             计算该bitmap的每行占用的内存*该bitmap的行数（除以1024转换为kb
               return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    public static MemoryCacheStrategy getInstance(){
        return MemoryHolder.instance;
    }
    private static class MemoryHolder{
        static final MemoryCacheStrategy instance = new MemoryCacheStrategy();
    }
    @Override
    public void putImage(String address, Bitmap bitmap, CompressStrategy compressStrategy, CompressStrategy.Parameters parameters) {
        mCache.put(address,compressStrategy.compress(bitmap,parameters));
    }

    @Override
    public Bitmap getImage(String address, CompressStrategy.Parameters parameters) {
        return mCache.get(address);
    }

}
