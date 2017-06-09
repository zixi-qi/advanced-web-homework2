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
    private String location;
    private String tel;
    private int age;

    public Set<Person> getFriends() {
        return friends;
    }

    public void setFriends(Set<Person> friends) {
        this.friends = friends;
    }

    @Relationship(type = "FRIENDSHIP",direction = Relationship.UNDIRECTED)
    private Set<Person> friends;

    public Person(){}

    public Person(String name,String location,String tel,int age){
        this.name = name;
        this.location = location;
        this.tel = tel;
        this.age = age;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString(){
        return String.format("%s@id%s",getName(),getId().toString());
    }

}
