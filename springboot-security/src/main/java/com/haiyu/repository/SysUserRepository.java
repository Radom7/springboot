package com.haiyu.repository;

import com.haiyu.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Title: SysUserRepository
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/6/6 18:00
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    SysUser findByUsername(String username);
}