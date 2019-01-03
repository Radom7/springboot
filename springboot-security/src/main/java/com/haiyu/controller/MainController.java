package com.haiyu.controller;

import com.haiyu.entity.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Title: MainController
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/6/6 11:48
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String index(Model model) {
        Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "index";
    }

}
