package com.haiyu.mapper.test1;


import com.haiyu.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Title: UserMapper
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/8/12 12:18
 */
@Repository
public interface User1Mapper {
    List<User> getAll();

    User getOne(Long id);

    void insert(User user);

    void update(User user);

    void delete(Long id);
}
