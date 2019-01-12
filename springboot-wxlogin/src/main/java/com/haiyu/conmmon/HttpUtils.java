package com.haiyu.conmmon;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Desc: http请求工具类
 * @Author: liuxing
 * @Date: 2019/1/12 10:37
 * @Version 1.0
 */
public class HttpUtils {

    public static String doGet(String url, Map<String,String> param){
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        CloseableHttpResponse response = null;

        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if(param != null){
                for (String key : param.keySet()){
                    builder.addParameter(key,param.get(key));
                }
            }

            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpClient.execute(httpGet);

            // 判断返回状态是否为200
            if(response.getStatusLine().getStatusCode() == 200){
                result = EntityUtils.toString(response.getEntity(),"UTF-8");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(response != null){
                    response.close();
                }
                httpClient.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String doGet(String url){
        return doGet(url,null);
    }

    public static String doPost(String url, Map<String,String> param){
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        CloseableHttpResponse response = null;

        try {
            // 创建http POST请求
            HttpPost httpPost = new HttpPost(url);

            // 创建参数列表
            if(param != null){
                List<NameValuePair> pairList = new ArrayList<>();
                for (String key : param.keySet()){
                    pairList.add(new BasicNameValuePair(key,param.get(key)));
                }
            }

            // 执行请求
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(),"UTF-8");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(response != null){
                    response.close();
                }
                httpClient.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String doPost(String url){
        return doPost(url,null);
    }


    public static String doPostJson(String url, String json){
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        CloseableHttpResponse response = null;

        try {
            // 创建http POST请求
            HttpPost httpPost = new HttpPost(url);

            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            // 执行请求
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(),"UTF-8");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(response != null){
                    response.close();
                }
                httpClient.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return result;
    }

}
