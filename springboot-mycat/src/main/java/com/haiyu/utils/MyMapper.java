package com.haiyu.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Title: MyMapper
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/8/31 15:40
 */
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T> {

}
