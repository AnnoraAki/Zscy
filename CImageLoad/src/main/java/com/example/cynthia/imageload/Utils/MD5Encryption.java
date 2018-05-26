package com.example.cynthia.imageload.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 利用MD5方式给文件加密
 */

public class MD5Encryption implements Encryption {
    @Override
    public String encrypt(String str) {
        String result = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] data = md5.digest(str.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte temp : data) {
//              每个字节转换为16进制字符串
                String toStr = Integer.toHexString(temp & 0xff);
//              不足16位（占位1）用0补
                builder.append(toStr.length() == 1 ? "0" + toStr : toStr);
            }
            result = builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
