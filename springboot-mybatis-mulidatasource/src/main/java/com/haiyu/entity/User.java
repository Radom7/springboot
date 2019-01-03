package com.haiyu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: User
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/8/12 12:12
 */
@Data
public class User implements Serializable{
    private long id;
    private String name;
    private String sex;
    private Integer age;
}
