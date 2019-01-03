package com.haiyu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 林尤庆
 * @date 2018年3月22日 下午4:20:57
 * @version 1.0
 */
@Controller
@RequestMapping("/")
public class MainController {
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/toLogin")
	public String toLogin(){
		return "login";
	}
	
	@RequestMapping("/unAuth")
	public String unAuth(){
		return "unauth";
	}

}
