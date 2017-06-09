package com.advancedweb.service;

import com.advancedweb.entity.Person;
import com.advancedweb.exception.InvalidArgumentException;
import com.advancedweb.exception.MyException;
import com.advancedweb.exception.PersonNotFoundException;
import com.advancedweb.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Alex on 2017/6/9.
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person getPersonById(Long id) throws MyException{
        if (id==null){
            throw new InvalidArgumentException();
        }
        Person person = personRepository.findOne(id);
        if (person==null){
            throw new PersonNotFoundException();
        }
        return person;
    }

    public void addPerson(Person person) throws MyException{
        if (person==null||person.getName()==null){
            throw new InvalidArgumentException();
        }
        personRepository.save(person);
    }

    public void deletePerson(Person person) throws MyException{
        if (person==null||person.getName()==null){
            throw new InvalidArgumentException();
        }
        personRepository.delete(person);
    }

    public void clearDatabase(){
        personRepository.deleteAll();
    }

    public void addFriendShip(Person x,Person y) throws MyException{
        if (x==null||y==null||x.getId()==null||y.getId()==null||x.getName()==null||y.getName()==null){
            throw new InvalidArgumentException();
        }
        x.makeNewFriend(y);
        personRepository.save(x);
    }

    public List<String> findShortestFriendshipPaths(Person x,Person y) throws MyException{
        if (x==null||y==null||x.getId()==null||y.getId()==null||x.getName()==null||y.getName()==null){
            throw new InvalidArgumentException();
        }
        List<String> result = new ArrayList<>();
        if (Objects.equals(x.getId(), y.getId())){
            result.add(x.toString());
            return result;
        }
        Iterable<Map<String, Iterable<Object>>> paths = personRepository.findShortestPaths(x.getId(),y.getId());
        for (Map<String, Iterable<Object>> path:paths){
            String currentPath = null;
            Iterable<Object> persons = path.get("nodes(paths)");
            for (Object object:persons){
                Person person = (Person)object;
                if (currentPath==null){
                    currentPath = person.toString();
                    continue;
                }
                currentPath = currentPath+"->"+person.toString();
            }
            result.add(currentPath);
        }
        return result;
    }



}
