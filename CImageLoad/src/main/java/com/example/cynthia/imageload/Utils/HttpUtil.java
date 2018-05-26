package com.example.cynthia.imageload.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
    public static Bitmap getBitmap(String url) throws IOException {
        URL mUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) mUrl.openConnection();
        conn.setDoInput(true);
        conn.connect();
        InputStream is = conn.getInputStream();
        if (conn.getResponseCode() == 200) {
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            conn.disconnect();
            return bitmap;
        } else {
            conn.disconnect();
            return null;
        }
    }
}
