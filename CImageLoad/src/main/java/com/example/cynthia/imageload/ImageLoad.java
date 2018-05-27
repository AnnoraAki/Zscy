package com.example.cynthia.imageload;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.example.cynthia.imageload.CacheStrategy.CacheStrategy;
import com.example.cynthia.imageload.CompressStrategy.CompressStrategy;
import com.example.cynthia.imageload.Utils.Callback;
import com.example.cynthia.imageload.Utils.HttpUtil;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ImageLoad {

    private static final Handler UI_HANDLER = new Handler();
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    private static ExecutorService sFixedThreadPool = new ThreadPoolExecutor(THREAD_COUNT,
            THREAD_COUNT, 0L, TimeUnit.MILLISECONDS,
            new MyLinkedBlockingDeque<Runnable>());

    public static void show(final ImageView imageView, final ConfigSetting setting) {
        CompressStrategy.Parameters parameter = setting.getParameters();
        imageView.setImageDrawable(setting.getmWaiting());
        imageView.setScaleType(parameter.scaleType);
        imageView.setTag(setting.getmUrl());
        getThis(setting, new Callback() {
            @Override
            public void success(Bitmap bitmap) {
                if (imageView.getTag().equals(setting.getmUrl()))
                    imageView.setImageBitmap(bitmap);
            }

            @Override
            public void fail(Exception e) {
                e.printStackTrace();
                imageView.setImageDrawable(setting.getmError());
            }
        });
    }

    private static void getThis(final ConfigSetting setting, final Callback callback) {
        sFixedThreadPool.submit(new Runnable() {
            @Override
            public void run() {
//              缓存内获取
                CacheStrategy cacheStrategy = setting.getmCacheStrategy();
                Bitmap bitmap = cacheStrategy.getImage(setting.getmUrl(), setting.getParameters());
                if (bitmap != null) {
                    backUIThread(bitmap, callback, null);
                    return;
                }
                getImage(setting, callback);
            }
        });
    }

    private static void backUIThread(final Bitmap bitmap, final Callback callback, final Exception e) {
        UI_HANDLER.post(new Runnable() {
            @Override
            public void run() {
                if (e == null)
                    callback.success(bitmap);
                else
                    callback.fail(e);

            }
        });
    }

    private static void getImage(ConfigSetting setting, Callback callback) {
        String address = setting.getmUrl();
        Bitmap bitmap = null;
        try {
            bitmap = address.startsWith("https") ? withHttp(address) : withFile(address);
        } catch (IOException e) {
            e.printStackTrace();
            backUIThread(bitmap, null, e);
        }
        if (bitmap != null) {
            CompressStrategy compressStrategy = setting.getmCompressStrategy();
            CompressStrategy.Parameters parameters = setting.getParameters();
            backUIThread(compressStrategy.compress(bitmap, parameters), callback, null);
            CacheStrategy cacheStrategy = setting.getmCacheStrategy();
            cacheStrategy.putImage(address, bitmap, compressStrategy, parameters);
        }
    }

    private static Bitmap withHttp(String address) throws IOException {
       Bitmap bitmap = HttpUtil.getBitmap(address);
       return bitmap;
    }

    private static Bitmap withFile(String address){
        return BitmapFactory.decodeFile(address);
    }

    /**
     * 重写实现栈式加载图片
     *
     * @param <T>
     */

    private static class MyLinkedBlockingDeque<T> extends LinkedBlockingDeque<T> {
        @Override
        public T take() throws InterruptedException {
            return takeLast();
        }

        @Override
        public T poll() {
            return pollLast();
        }
    }
}
