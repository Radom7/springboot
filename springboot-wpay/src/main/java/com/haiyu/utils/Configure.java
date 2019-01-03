package com.haiyu.utils;

/**
 * @Title: Configure
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/6/5 11:34
 */
public class Configure {
    private static String key = "022ad1995704907f0db176d84126bab7";

    //小程序ID
    private static String appID = "wx2537437d11cdec0b";
    //商户号
    private static String mch_id = "1381483602";
    //
    private static String secret = "022ad1995704907f0db176d84126bab7";

    public static String getSecret() {
        return secret;
    }

    public static void setSecret(String secret) {
        Configure.secret = secret;
    }

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        Configure.key = key;
    }

    public static String getAppID() {
        return appID;
    }

    public static void setAppID(String appID) {
        Configure.appID = appID;
    }

    public static String getMch_id() {
        return mch_id;
    }

    public static void setMch_id(String mch_id) {
        Configure.mch_id = mch_id;
    }

}
