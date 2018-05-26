package com.example.cynthia.imageload.CacheStrategy;

import android.graphics.Bitmap;

import com.example.cynthia.imageload.CompressStrategy.CompressStrategy;
import com.example.cynthia.imageload.CompressStrategy.LosslessCompressStrategy;

public class BothCacheStrategy implements CacheStrategy {
    private MemoryCacheStrategy memoryCacheStrategy = MemoryCacheStrategy.getInstance();
    private DiskCacheStrategy diskCacheStrategy = DiskCacheStrategy.getInstance();

    private BothCacheStrategy(){}
    public static BothCacheStrategy getInstance(){
        return BothHolder.instance;
    }
    private static class BothHolder{
        public static final BothCacheStrategy instance = new BothCacheStrategy();
    }

    public void setPath(String path){
        diskCacheStrategy.setPath(path);
    }

    @Override
    public void putImage(String address, Bitmap bitmap, CompressStrategy compressStrategy, CompressStrategy.Parameters parameters) {
        memoryCacheStrategy.putImage(address, bitmap, compressStrategy, parameters);
        diskCacheStrategy.putImage(address, bitmap, compressStrategy, parameters);
    }

    @Override
    public Bitmap getImage(String address, CompressStrategy.Parameters parameters) {
        Bitmap bitmap = memoryCacheStrategy.getImage(address,parameters);
        if (bitmap == null){
            bitmap = diskCacheStrategy.getImage(address,parameters);
            if (bitmap != null)
                memoryCacheStrategy.putImage(address, bitmap, LosslessCompressStrategy.getInstance(),parameters);
        }
        return bitmap;
    }
}
