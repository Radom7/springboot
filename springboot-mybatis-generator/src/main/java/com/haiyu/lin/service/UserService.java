package com.haiyu.lin.service;

import com.haiyu.lin.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2018/5/20.
 */

public interface UserService {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int insertByBatch(List <User> userList);
}
