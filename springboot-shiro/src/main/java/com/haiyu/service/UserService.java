package com.haiyu.service;

import java.util.List;

import com.haiyu.domain.User;

/**
 * @author 林尤庆
 * @date 2018年3月23日 上午11:14:42
 * @version 1.0
 */
public interface UserService {
	User findByName(String name);
	
	List<String> findPermissionByUserId(Integer userId);
}
