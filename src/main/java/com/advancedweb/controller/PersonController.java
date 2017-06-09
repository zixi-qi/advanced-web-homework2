package com.advancedweb.controller;

import com.advancedweb.entity.Person;
import com.advancedweb.exception.MyException;
import com.advancedweb.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Alex on 2017/6/9.
 */
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    //create a person,return id
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Long addPerson(@RequestBody Person person){
        try {
            personService.addPerson(person);
        } catch (MyException e) {
            return null;
        }
        return person.getId();
    }

    //delete a person
    @RequestMapping(value = "",method = RequestMethod.DELETE)
    public void deletePerson(@RequestParam("id") Long id){
        try {
            Person person = personService.getPersonById(id);
            if (person!=null){
                personService.deletePerson(person);
            }
        } catch (MyException ignored) {
        }
    }

    //retrive person info by id
    @RequestMapping(value = "",method = RequestMethod.GET)
    public Person retrievePerson(@RequestParam("id") Long id){
        try {
            return personService.getPersonById(id);
        } catch (MyException ignored) {
            return null;
        }
    }

    //get friendship shortest paths
    @RequestMapping(value = "/friendpath",method = RequestMethod.GET)
    public List<String> getFriendPath(@RequestParam("id1") Long id1,@RequestParam("id2") Long id2){
        try {
            Person person1 = personService.getPersonById(id1);
            Person person2 = personService.getPersonById(id2);
            return personService.findShortestFriendshipPaths(person1,person2);
        } catch (MyException e) {
            return null;
        }
    }

    //add friendship
    @RequestMapping(value = "/friendpath",method = RequestMethod.POST)
    public void addFriendship(@RequestParam("id1") Long id1,@RequestParam("id2") Long id2){
        try {
            Person person1 = personService.getPersonById(id1);
            Person person2 = personService.getPersonById(id2);
            personService.addFriendShip(person1,person2);
        } catch (MyException ignored) {
        }
    }

}
