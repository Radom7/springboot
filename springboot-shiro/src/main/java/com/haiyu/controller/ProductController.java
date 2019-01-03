package com.haiyu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 林尤庆
 * @date 2018年3月22日 下午4:23:52
 * @version 1.0
 */
@Controller
@RequestMapping("/product")
public class ProductController {

	@RequestMapping("/toAdd")
	public String toAdd(){
		return "product/add";
	}
	
	@RequestMapping("/toList")
	public String toList(){
		return "product/list";
	}
	
	@RequestMapping("/toUpdate")
	public String toUpdate(){
		return "product/update";
	}
}

