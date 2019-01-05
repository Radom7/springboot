package com.haiyu.controller;

import com.haiyu.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @Title: WebFluxReactiveController
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/7/26 14:17
 */
@RestController
public class WebFluxReactiveController {

    @Autowired
    private ReactiveRedisTemplate reactiveRedisTemplate;

    /**
     * 新增一个 Person
     */
    @PostMapping("/redisReactive/save")
    public Mono<Person> add(@RequestBody Person person){
        String key = "person_" + person.getId();
        ReactiveValueOperations<String, Person> operations = reactiveRedisTemplate.opsForValue();
        return operations.getAndSet(key,person);
    }

    @GetMapping(value = "/redisReactive/{id}")
    public Mono<Person> findPersonById(@PathVariable("id") Long id) {
        String key = "person_" + id;
        ReactiveValueOperations<String, Person> operations = reactiveRedisTemplate.opsForValue();
        Mono<Person> person = operations.get(key);
        return person;
    }

    @DeleteMapping(value = "/redisReactive/delete/{id}")
    public Mono<Long> deletePerson(@PathVariable("id") Long id) {
        String key = "person_" + id;
        return reactiveRedisTemplate.delete(key);
    }


}
