package com.advancedweb.repository;

import com.advancedweb.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by Alex on 2017/6/9.
 */
@Repository
public interface PersonRepository extends Neo4jRepository<Person,Long> {

    Person findById(Long id);

}
