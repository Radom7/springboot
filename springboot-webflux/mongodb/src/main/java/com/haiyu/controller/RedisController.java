package com.haiyu.controller;

import com.haiyu.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * @Title: RedisController
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/7/26 13:47
 */
@RestController
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 新增一个 Person
     */
    @PostMapping("/redis/save")
    public Mono<Person> add(@RequestBody Person person){
        String key = "person_" + person.getId();
        ValueOperations<String, Person> operations = redisTemplate.opsForValue();
        operations.set(key, person, 600, TimeUnit.SECONDS);
        return Mono.create(monoSink -> monoSink.success(person));
    }

    @GetMapping(value = "/redis/{id}")
    public Mono<Person> findPersonById(@PathVariable("id") Long id) {
        String key = "person_" + id;
        ValueOperations<String, Person> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        Person person = operations.get(key);
        if (!hasKey) {
            return Mono.create(monoSink -> monoSink.success(null));
        }
        return Mono.create(monoSink -> monoSink.success(person));
    }

    @DeleteMapping(value = "/redis/delete/{id}")
    public Mono<Long> deletePerson(@PathVariable("id") Long id) {
        String key = "person_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
        }
        return Mono.create(monoSink -> monoSink.success(id));
    }


}
