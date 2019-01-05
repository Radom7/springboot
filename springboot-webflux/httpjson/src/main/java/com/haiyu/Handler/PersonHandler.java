package com.haiyu.Handler;

import com.haiyu.dao.PersonRepository;
import com.haiyu.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Title: PersonHandler
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/7/6 17:06
 */
@Component
public class PersonHandler {
    private final PersonRepository personHandler;

    @Autowired
    public PersonHandler(PersonRepository personHandler) {
        this.personHandler = personHandler;
    }

    public Mono<Long> save(Person person) {
        return Mono.create(personMonoSink -> personMonoSink.success(personHandler.save(person)));
    }

    public Mono<Person> findPersonById(Long id) {
        return Mono.justOrEmpty(personHandler.findPersonById(id));
    }

    public Flux<Person> findAllPerson() {
        return Flux.fromIterable(personHandler.findAll());
    }

    public Mono<Long> updatePerson(Person person) {
        return Mono.create(personMonoSink -> personMonoSink.success(personHandler.updatePerson(person)));
    }

    public Mono<Long> deletePerson(Long id) {
        return Mono.create(personMonoSink -> personMonoSink.success(personHandler.deletePerson(id)));
    }

}
