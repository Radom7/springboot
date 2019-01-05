package com.haiyu.controller;

import com.haiyu.Handler.PersonHandler;
import com.haiyu.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Title: PersonWebFluxController
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/7/6 17:32
 */
@RestController
@RequestMapping(value = "/person")
public class PersonWebFluxController {
    @Autowired
    private PersonHandler personHandler;

    @GetMapping(value = "/{id}")
    public Mono<Person> findPersonById(@PathVariable("id") Long id) {
        return personHandler.findPersonById(id);
    }

    @GetMapping("findAll")
    public Flux<Person> findAllPerson() {
        return personHandler.findAllPerson();
    }

    @PostMapping("save")
    public Mono<Long> save(@RequestBody Person person) {
        return personHandler.save(person);
    }

    @PutMapping("update")
    public Mono<Long> updatePerson(@RequestBody Person person) {
        return personHandler.updatePerson(person);
    }

    @DeleteMapping(value = "delete/{id}")
    public Mono<Long> deletePerson(@PathVariable("id") Long id) {
        return personHandler.deletePerson(id);
    }
}
