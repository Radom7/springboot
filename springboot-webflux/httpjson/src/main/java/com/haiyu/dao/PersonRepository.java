package com.haiyu.dao;

import com.haiyu.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Title: PersonRepository
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/7/6 17:03
 */
@Repository
public class PersonRepository {
    private ConcurrentMap<Long, Person> repository = new ConcurrentHashMap<>();

    private static final AtomicLong idGenerator = new AtomicLong(0);

    public Long save(Person person) {
        Long id = idGenerator.incrementAndGet();
        person.setId(id);
        repository.put(id, person);
        return id;
    }

    public Collection<Person> findAll() {
        return repository.values();
    }


    public Person findPersonById(Long id) {
        return repository.get(id);
    }

    public Long updatePerson(Person person) {
        repository.put(person.getId(), person);
        return person.getId();
    }

    public Long deletePerson(Long id) {
        repository.remove(id);
        return id;
    }
}
