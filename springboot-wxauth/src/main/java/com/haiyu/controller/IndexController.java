package com.haiyu.controller;

import com.alibaba.fastjson.JSONObject;
import com.haiyu.utils.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Title: IndexController
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2019/1/10 15:12
 */
@Controller
@Slf4j
public class IndexController {
    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("ok")
    public String ok(HttpServletRequest request) throws IOException {
        String code= request.getParameter("code");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + AuthUtil.APPID
                + "&secret=" + AuthUtil.APPSECRET
                + "&code=" + code
                + "&grant_type=authorization_code";

        JSONObject jsonObject = AuthUtil.doGetJson(url);
        log.info(jsonObject.toString());
        String openid = jsonObject.getString("openid");
        String token = jsonObject.getString("access_token");

        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?"
                + "access_token=" +token
                + "&openid=" + openid
                + "&lang=zh_CN";

        JSONObject uerInfo = AuthUtil.doGetJson(infoUrl);
        log.info(uerInfo.toString());

        return "ok";
    }
}
