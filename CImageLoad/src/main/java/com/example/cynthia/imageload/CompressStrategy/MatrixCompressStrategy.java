package com.example.cynthia.imageload.CompressStrategy;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * 等比例压缩图片
 */

public class MatrixCompressStrategy extends CompressStrategy {

    private MatrixCompressStrategy(){}
    private static MatrixCompressStrategy getInstance(){
        return MatrixHolder.instance;
    }
    private static class MatrixHolder{
        private static final MatrixCompressStrategy instance = new MatrixCompressStrategy();
    }

    @Override
    public Bitmap compress(Bitmap bitmap, Parameters parameter) {
        Matrix matrix = new Matrix();
        matrix.setScale(parameter.ratio, parameter.ratio);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0,bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);
        return bitmap;
    }
}
