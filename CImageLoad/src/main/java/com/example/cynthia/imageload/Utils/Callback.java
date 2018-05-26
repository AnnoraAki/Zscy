package com.example.cynthia.imageload.Utils;

import android.graphics.Bitmap;

public interface Callback {
    void success(Bitmap bitmap);
    void fail(Exception e);
}
