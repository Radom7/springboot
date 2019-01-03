package com.haiyu.controller;

import com.haiyu.entity.OrderInfo;
import com.haiyu.entity.OrderReturnInfo;

import com.haiyu.entity.Pay;
import com.haiyu.utils.*;
import com.thoughtworks.xstream.XStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Title: WeChatPay
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/6/5 11:45
 */
@Controller
public class WeChatPayController {


    private static final Logger L = LoggerFactory.getLogger(WeChatPayController.class);

    @RequestMapping(value = "wechat",method = RequestMethod.GET)
    @ResponseBody
    public String wechat(HttpServletRequest request) throws Exception{
        //String openid = request.getParameter("openid");

        String openid = "nklsnfkldsfnjklsdhnjksvnklds";
        String Key = "022ad1995704907f0db176d84126bab7";
        String appid = "wxb9ad32335e80941e";
        String mch_id = "1400956602";
        String spbill_create_ip = "112.74.56.75";
        String body = "测试微商城";
        String trade_type = "JSAPI";
        String notify_url = "https://wx.tiantue.com/decor-web/wechat/paycallback.do";
        int total_fee = 1;

        OrderInfo order = new OrderInfo();
        order.setAppid(appid);
        order.setMch_id(mch_id);
        order.setNonce_str(Util.getRandomStringByLength(32));
        order.setBody(body);
        order.setOut_trade_no(Util.getRandomStringByLength(32));
        order.setTotal_fee(total_fee);
        order.setSpbill_create_ip(spbill_create_ip);
        order.setNotify_url(notify_url);
        order.setTrade_type(trade_type);
        order.setOpenid(openid);
        order.setSign_type("MD5");
        //生成签名
        String sign = Sign.getSign(order);
        order.setSign(sign);

       /* XStream xStream = new XStream();
        xStream.alias("xml", OrderReturnInfo.class);
        String reqXml = xStream.toXML(order);
        System.out.println(reqXml);*/
        XSteram.xstream.alias("xml", OrderInfo.class);
        String reqXml = XSteram.xstream.toXML(order);
        reqXml = reqXml.replaceAll("__", "_");
        System.out.println(reqXml);

        String result = HttpsRequest.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", order);
        L.info("---------下单返回:"+result);


        //OrderReturnInfo returnInfo = (OrderReturnInfo)xStream.fromXML(result);
        return reqXml;
    }

    @RequestMapping(value = "get",method = RequestMethod.GET)
    @ResponseBody
    public String getOpenId(HttpServletRequest request) throws Exception{
        //String code = request.getParameter("code");
        String code = "000000081c1e4b9fc785e36ef2fbed2d9eb645e";
        String secret = "022ad1995704907f0db176d84126bab7";
        HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/sns/jscode2session?appid="+Configure.getAppID()+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code");
        //设置请求器的配置
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse res = httpClient.execute(httpGet);
        HttpEntity entity = res.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");
        return result;
    }
}
