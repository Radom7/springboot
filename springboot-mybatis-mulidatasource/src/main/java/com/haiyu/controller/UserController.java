package com.haiyu.controller;

import com.haiyu.entity.User;
import com.haiyu.mapper.test1.User1Mapper;
import com.haiyu.mapper.test2.User2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Title: UserController
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/8/12 12:34
 */
@RestController
public class UserController {

    @Autowired
    private User1Mapper user1Mapper;

    @Autowired
    private User2Mapper user2Mapper;

    @RequestMapping("/getUsers")
    public List<User> getUsers() {
        List<User> users=user1Mapper.getAll();
        return users;
    }

    @RequestMapping("/getUser/{id}")
    public User getUser(@PathVariable Long id) {
        User user=user2Mapper.getOne(id);
        return user;
    }

    @RequestMapping("/add")
    public void save(@RequestBody User user) {
        System.out.println(user);
        user2Mapper.insert(user);
    }

    @RequestMapping(value="update")
    public void update(@RequestBody User user) {
        user2Mapper.update(user);
    }

    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        user1Mapper.delete(id);
    }
}
