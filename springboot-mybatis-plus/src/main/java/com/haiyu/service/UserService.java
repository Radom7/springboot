package com.haiyu.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.haiyu.entity.User;


import java.util.List;

/**
 * Created by Administrator on 2018/5/16.
 */
public interface UserService extends IService<User>{

    boolean deleteAll();

    List<User> selectListBySQL();

    Page<User> selectUserPage(Page <User> page);
}
