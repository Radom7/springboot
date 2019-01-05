package com.haiyu.handler;

import com.haiyu.dao.PersonRepository;
import com.haiyu.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Title: PersonHandler
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/7/26 14:40
 */
@Component
public class PersonHandler {
    private Logger logger = LoggerFactory.getLogger(PersonHandler.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PersonRepository personRepository;

    public Mono<Person> save(Person person) {
        logger.info("保存数据到MongoDB!!!");
        return personRepository.save(person);
    }

    public Mono<Person> findPersonById(Long id){
        String key = "person_" + id;

        // 从缓存中获取个人信息
        ValueOperations<String, Person> operations = redisTemplate.opsForValue();

        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            Person person = operations.get(key);

            logger.info("PersonHandler.findPersonById() : 从缓存中获取了个人信息 >> " + person.toString());

            return Mono.create(personMonoSink -> personMonoSink.success(person));
        }

        // 从 MongoDB 中获取个人信息
        Mono<Person> personMono = personRepository.findById(id);

        if(personMono == null)
            return personMono;

        // 插入缓存
        personMono.subscribe(person -> {
            operations.set(key,person);
            logger.info("PersonHandler.findPersonById() : 个人信息插入缓存 >> " + person.toString());
        });

        return personMono;
    }

    public Flux<Person> findAllPerson(){
        return personRepository.findAll().cache();
    }

    public Mono<Person> updatePerson(Person person){
        Mono<Person> personMono = personRepository.save(person);

        String key = "person_" + person.getId();
        boolean haskey = redisTemplate.hasKey(key);
        if(haskey){
            redisTemplate.delete(key);

            logger.info("PersonHandler.modifyPerson() : 从缓存中删除了个人信息id >> " + person.getId());
        }

        return personMono;
    }

    public Mono<Void> deletePerson(Long id){

        // 缓存存在，删除缓存
        String key = "person_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);

            logger.info("CityHandler.deleteCity() : 从缓存中删除个人信息id >> " + id);
        }

        return personRepository.deleteById(id).then();
    }

}