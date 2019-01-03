package com.haiyu.entity;

import lombok.Data;

/**
 * @Title: Person
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/7/31 18:03
 */
@Data
public class Person {
    private Long id;
    private String name;
    private Integer age;
    private String work;
}
