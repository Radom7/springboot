package com.haiyu.dao;

import com.haiyu.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @Title: PersonRepository
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/8/16 9:57
 */
public interface PersonRepository extends ElasticsearchRepository<Person,Long> {

    Person queryPersonById(Long id);

    List<Person> findByName(String name);

    List<Person> findByNameAndWork(String name, String work);

    Page<Person> findByWork(String work, Pageable pageable);

}
