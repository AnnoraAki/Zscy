package com.example.cynthia.zscy.Utils;

import android.widget.Toast;

public class ToastUtils {
    public static void showError(String error){
        Toast.makeText(Application.getContext(),error,
                Toast.LENGTH_LONG).show();
    }

    public static void showResponse(String info){
        Toast.makeText(Application.getContext(),info,
                Toast.LENGTH_SHORT).show();
    }
}
