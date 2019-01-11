package com.haiyu.controller;

import com.haiyu.utils.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @Title: LoginController
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2019/1/10 14:21
 */
@Controller
@RequestMapping("/wx")
@Slf4j
public class LoginController {

    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String backUrl = "http://linliuxing.top:8090/ok";
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                +"appid=" + AuthUtil.APPID
                +"&redirect_uri="+ URLEncoder.encode(backUrl,"UTF-8")
                +"&response_type=code"
                +"&scope=snsapi_userinfo"
                +"&state=STATE#wechat_redict";

        response.sendRedirect(url);
    }

}
