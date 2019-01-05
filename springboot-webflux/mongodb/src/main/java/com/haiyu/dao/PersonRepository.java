package com.haiyu.dao;

import com.haiyu.entity.Person;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Title: PersonRepository
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/7/25 17:26
 */
@Repository
@Primary
public interface PersonRepository extends ReactiveMongoRepository<Person,Long> {
}
