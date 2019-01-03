package com.haiyu.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @Title: Person
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/8/16 9:44
 */
@Data
@Document(indexName="data",type="person")
public class Person implements Serializable{
    private Long id;
    private String name;
    private Integer age;
    private String work;

    public Person() {
    }

    public Person(Long id, String name, Integer age, String work) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.work = work;
    }
}
