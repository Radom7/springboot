package com.haiyu.service.impl;

import java.util.List;
import com.haiyu.dao.UserMapper;
import com.haiyu.domain.User;
import com.haiyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 林尤庆
 * @date 2018年3月23日 上午11:16:07
 * @version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	public User findByName(String name) {
		return userMapper.findByName(name);
	}

	public List<String> findPermissionByUserId(Integer userId) {
		
		return userMapper.findPermissionByUserId(userId);
	}
	
}
