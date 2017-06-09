package com.advancedweb.repository;

import com.advancedweb.entity.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;

/**
 * Created by Alex on 2017/6/9.
 */
@Repository
public interface PersonRepository extends Neo4jRepository<Person,Long> {

    @Query("START startNode = node({startId}), endNode = node({endId}) " +
            "MATCH paths = allShortestPaths((startNode)-[*]-(endNode)) " +
            "RETURN nodes(paths)")
    Iterable<Map<String, Iterable<Object>>> findShortestPaths(@Param("startId") long startId, @Param("endId") long endId);


}
