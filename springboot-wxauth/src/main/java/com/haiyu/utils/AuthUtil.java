package com.haiyu.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Title: AuthUtil
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2019/1/10 14:02
 */
public class AuthUtil {

    public static final String APPID = "wxf59660c089a04881";

    public static final String APPSECRET = "dd534c1a2b414aed73d639d80f304a26";

    public static JSONObject doGetJson(String url) throws IOException {
        JSONObject jsonObject = null;
        DefaultHttpClient  client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (entity != null){
            String result = EntityUtils.toString(entity,"UTF-8");
            jsonObject = JSONObject.parseObject(result);
        }
        httpGet.releaseConnection();
        return jsonObject;
    }

}
