package com.lrb.sys.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author lrbin
 * @version 1.0.0
 * @company
 * @create 2019/12/3 14:53
 * @Description
 */
public class MDUtil {
    //盐值
    private static final String SALT = "skdjfl";

    public static String md5(String password) {
        String result = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("md5");
//            md = MessageDigest.getInstance("sha");
            md.update((password + SALT).getBytes());
            //加密后的密文(32位),可以多次加密
            result = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
