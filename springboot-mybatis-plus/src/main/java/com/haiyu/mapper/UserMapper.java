package com.haiyu.mapper;


import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.haiyu.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * Created by Administrator on 2018/5/16.
 */
public interface UserMapper extends BaseMapper<User> {
    int deleteAll();

    @SqlParser(filter = true)
    @Select("select id , name, age from user")
    List<User> selectListBySQL();

    List<User> selectUserList(Pagination page);

}
