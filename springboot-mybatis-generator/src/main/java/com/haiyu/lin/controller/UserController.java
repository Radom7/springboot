package com.haiyu.lin.controller;

import com.haiyu.lin.entity.User;
import com.haiyu.lin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/20.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/batchTest")
    public String batchTest() {
        List<User> userList = new ArrayList<>();
        User user =  new User();
        for(int i = 0;i < 10;i++){
            user.setId(i+1);
            user.setName("alice"+i);
            user.setAge(16+1);
            userList.add(user);
        }
        int ok = userService.insertByBatch(userList);
        if(ok != 0){
            System.out.println("成功插入");
            return "成功插入";
        }else{
            System.out.println("失败！！！");
            return "失败！！";
        }
    }
}
