package com.example.cynthia.imageload.Utils;

import java.security.NoSuchAlgorithmException;

/**
 * 加密接口
 * 进行磁盘储存的时候使用
 */
public interface Encryption {
    /**
     *
     * @param str 待加密内容
     * @return 加密后返回值
     */
    String encrypt(String str) throws NoSuchAlgorithmException;
}
