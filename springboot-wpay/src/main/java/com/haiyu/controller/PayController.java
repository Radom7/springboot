package com.haiyu.controller;

import com.haiyu.entity.Pay;
import com.haiyu.utils.HttpsRequest;
import com.haiyu.utils.Sign;
import com.haiyu.utils.Util;
import com.haiyu.utils.XSteram;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Title: PayController
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/6/5 9:55
 */
@Controller
public class PayController {

    @RequestMapping(value = "pay",method = RequestMethod.GET)
    public String pay(Model model) {


        String Key = "javastruts2springhibernate2016tr";
        String appid = "wx2537437d11cdec0b";
        String mch_id = "1381483602";
        String spbill_create_ip = "49.221.62.131";
        String body = "测试微商城";
        String trade_type = "NATIVE";
        String notify_url = "http://localhost/notify";

        /*String Key = "022ad1995704907f0db176d84126bab7";
        String appid = "wxb9ad32335e80941e";
        String mch_id = "1400956602";
        String spbill_create_ip = "112.74.56.75";
        String body = "测试微商城";
        String trade_type = "JSAPI";
        String notify_url = "http://localhost/notify";*/

        int total_fee = 1;

        String nonce_str = Util.getRandomString(20);
        String out_trade_no = Util.getCurrTime() + Util.getRandomString(4);

        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();

        parameters.put("appid", appid);
        parameters.put("mch_id", mch_id);
        parameters.put("body", body);
        parameters.put("spbill_create_ip", spbill_create_ip);
        parameters.put("trade_type", trade_type);
        parameters.put("notify_url", notify_url);
        parameters.put("nonce_str", nonce_str);
        parameters.put("out_trade_no", out_trade_no);
        parameters.put("total_fee", total_fee);
        String sign = Sign.Sign(parameters, Key);

        Pay pay = new Pay();
        pay.setAppid(appid);
        pay.setBody(body);
        pay.setMch_id(mch_id);
        pay.setNonce_str(nonce_str);
        pay.setNotify_url(notify_url);
        pay.setOut_trade_no(out_trade_no);
        pay.setSign(sign);
        pay.setSpbill_create_ip(spbill_create_ip);
        pay.setTotal_fee(total_fee);
        pay.setTrade_type(trade_type);

        XSteram.xstream.alias("xml", Pay.class);
        String reqXml = XSteram.xstream.toXML(pay);
        reqXml = reqXml.replaceAll("__", "_");

        System.out.println("reqXml:"+reqXml);

        String respxml = null;
        String requestUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        try {
            respxml = HttpsRequest.httpsRequest(requestUrl, "POST", reqXml);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        System.out.println("respxml:" + respxml);

        String codeurl = null;
        try {
            Document doc = DocumentHelper.parseText(respxml);
            Element root = doc.getRootElement();
            List<Element> elementList = root.elements();
            for (int i = 0; i < elementList.size(); i++) {

                if (elementList.get(i).getName().equals("code_url")) {
                    codeurl = elementList.get(i).getTextTrim();

                }
            }

        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String codeapiUrl = "http://pan.baidu.com/share/qrcode?w=300&h=300&url=URL";
        String ercodeUrl = codeapiUrl.replaceAll("URL", codeurl);

        System.out.println(ercodeUrl);

        //request.put("ercodeUrl", ercodeUrl);
        model.addAttribute("ercodeUrl", ercodeUrl);
        return "index";


    }


    @RequestMapping(value = "notify",method = RequestMethod.GET)
    public void notify(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("hahahahah");
        InputStream inputStream = null;
        inputStream = request.getInputStream();
        StringBuffer sb = new StringBuffer();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String s;
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        in.close();
        inputStream.close();

        String respxml = sb.toString();

        System.out.println("respxml" + respxml);

        String code;

        Document doc = DocumentHelper.parseText(respxml);
        Element root = doc.getRootElement();
        List<Element> elementList = root.elements();
        for (int i = 0; i < elementList.size(); i++) {

            if (elementList.get(i).getName().equals("result_code")) {
                code = elementList.get(i).getTextTrim();

                System.out.println(code);

                if (code.equals("SUCCESS")) {

                    String resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                            + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    //HttpServletResponse response = ServletActionContext.getResponse();
                    BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
                    out.write(resXml.getBytes());
                    out.flush();

                    out.close();

                    System.out.println("回复成功");

                }

            }
        }

    }
}
