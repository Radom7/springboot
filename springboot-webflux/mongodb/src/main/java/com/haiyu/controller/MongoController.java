package com.haiyu.controller;

import com.haiyu.dao.PersonRepository;
import com.haiyu.entity.Person;
import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * @Title: PersonController
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/7/25 17:28
 */
@RestController
@AllArgsConstructor
public class MongoController {

    @Autowired
    private PersonRepository personRepository;


    /**
     * 正常 MVC 模式
     */
    @GetMapping("/")
    public String hello(){
        return "hello!";
    }

    /**
     * 新增一个 Person
     */
    @PostMapping("/mongo")
    public Mono<Void> add(@RequestBody Person person){
        return personRepository.insert(person).then();
    }

    /**
     * 根据 ID 查询 Person
     */
    @GetMapping("/mongo/{id}")
    public Mono<Person> getById(@PathVariable Long id){
        return personRepository.findById(id);
    }

    /**
     * 查询所有 Person
     */
    @GetMapping("/mongo/list")
    public Flux<Person> list(){
        return personRepository.findAll();
    }

    /**
     * 删除指定 Person
     */
    @DeleteMapping("/mongo/delete/{id}")
    public Mono<Void> delete(@PathVariable Long id){
        return personRepository.deleteById(id).then();
    }

}


