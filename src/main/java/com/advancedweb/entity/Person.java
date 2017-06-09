package com.advancedweb.entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alex on 2017/6/9.
 */
@NodeEntity
public class Person {

    @GraphId
    private Long id;

    private String name;
    @Relationship(type = "FRIENDSHIP",direction = Relationship.UNDIRECTED)
    private Set<Person> friends;

    public Person(){}

    public Person(String name){
        this.name = name;
    }

    public void makeNewFriend(Person person){
        if (friends==null){
            friends = new HashSet<>();
        }
        if (friends.contains(person)){
            return;
        }
        friends.add(person);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
