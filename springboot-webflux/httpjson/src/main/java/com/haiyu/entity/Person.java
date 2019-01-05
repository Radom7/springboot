package com.haiyu.entity;

/**
 * @Title: Person
 * @Description: 实体类
 * @author: youqing
 * @version: 1.0
 * @date: 2018/7/6 16:58
 */
public class Person {
    private Long id;
    private String name;
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
