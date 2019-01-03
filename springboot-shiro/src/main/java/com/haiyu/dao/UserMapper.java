package com.haiyu.dao;


import java.util.List;

import com.haiyu.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 林尤庆
 * @date 2018年3月23日 上午10:40:08
 * @version 1.0
 */
@Mapper
@Repository
public interface UserMapper {
	/**
	 * 根据用户名查询用户
	 * @param name
	 * @return
	 */
	User findByName(String name);
	
	/**
	 * 根据用户ID查询用户拥有的资源授权码
	 */
	 List<String> findPermissionByUserId(Integer userId);
	
	/**
	 * 更新用户密码的方法
	 */
	 void updatePassword(User user);
}
