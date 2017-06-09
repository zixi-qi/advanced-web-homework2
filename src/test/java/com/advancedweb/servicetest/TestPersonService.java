package com.advancedweb.servicetest;

import com.advancedweb.entity.Person;
import com.advancedweb.exception.MyException;
import com.advancedweb.repository.PersonRepository;
import com.advancedweb.service.PersonService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
/**
 * Created by Qizixi on 2017/6/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPersonService {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    //test data
    Person jack,john,lee,jessica,jane;

    @Before
    public void setUp(){
        jack = new Person("jack","beijing","123456",18);
        john = new Person("john","shanghai","234567",19);
        lee = new Person("lee","nanjing","345678",20);
        jessica = new Person("jessica","suzhou","456789",21);
        jane = new Person("jane","hangzhou","5678980",22);
    }

    @Test
    public void test() throws MyException {
        personService.addPerson(jack);
        personService.addPerson(john);
        personService.addPerson(lee);
        personService.addPerson(jessica);
        personService.addPerson(jane);
        //set friendships
        //test friend paths from jack to jane
        //friendship:jack-lee-jane
        personService.addFriendShip(jack,lee);
        personService.addFriendShip(lee,jane);
        String friendPath1 = jack.toString()+"->"+lee.toString()+"->"+jane.toString();
        //friendship:jack-john-jessica-jane
        personService.addFriendShip(jack,john);
        personService.addFriendShip(john,jessica);
        personService.addFriendShip(jessica,jane);
        String friendPath2 = jack.toString()+"->"+john.toString()+"->"+jessica.toString()+"->"+jane.toString();
        //friendship:jack-john-jane
        personService.addFriendShip(john,jane);
        String friendPath3 = jack.toString()+"->"+john.toString()+"->"+jane.toString();
        //test find friendpaths
        List<String> paths = personService.findShortestFriendshipPaths(jack,jane);
        //there are 2 shortest paths:path1 and path3
        assertEquals(2,paths.size());
        assertTrue(paths.contains(friendPath1));
        assertTrue(paths.contains(friendPath3));
    }

    @After
    public void tearDown(){
        personService.clearDatabase();
    }
}
