package com.haiyu.controller;

import com.haiyu.dao.PersonRepository;
import com.haiyu.entity.Person;
import com.haiyu.handler.PersonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Title: PersonHandlerController
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/7/26 15:06
 */
@RestController
@RequestMapping("/person")
public class PersonHandlerController {

    @Autowired
    private PersonHandler personHandler;


    /**
     *
     * 功能描述: 根据id查找数据
     *
     * @param: 
     * @return: 
     * @auther: youqing
     * @date: 2018/7/26 15:50
     */
    @GetMapping(value = "/{id}")
    public Mono<Person> findPersonById(@PathVariable("id") Long id) {
        return personHandler.findPersonById(id);
    }

    /**
     *
     * 功能描述: 查找所有的数据
     *
     * @param: 
     * @return: 
     * @auther: youqing
     * @date: 2018/7/26 15:51
     */
    @GetMapping()
    public Flux<Person> findAllPerson() {
        return personHandler.findAllPerson();
    }

    /**
     *
     * 功能描述: 保存数据
     *
     * @param: 
     * @return: 
     * @auther: youqing
     * @date: 2018/7/26 15:51
     */
    @PostMapping()
    public Mono<Person> savePerson(@RequestBody Person person) {
        return personHandler.save(person);
    }

    /**
     *
     * 功能描述: 根据id更新数据
     *
     * @param:
     * @return: 
     * @auther: youqing
     * @date: 2018/7/26 15:52
     */
    @PutMapping()
    public Mono<Person> updatePerson(@RequestBody Person person) {
        return personHandler.updatePerson(person);
    }

    /**
     *
     * 功能描述: 根据id删除数据
     *
     * @param: 
     * @return: 
     * @auther: youqing
     * @date: 2018/7/26 15:52
     */
    @DeleteMapping(value = "delete/{id}")
    public Mono<Void> deletePerson(@PathVariable("id") Long id) {

        return personHandler.deletePerson(id);
    }

}
