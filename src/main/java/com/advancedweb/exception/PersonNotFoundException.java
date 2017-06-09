package com.advancedweb.exception;

/**
 * Created by Qizixi on 2017/6/9.
 */
public class PersonNotFoundException extends MyException {

    public PersonNotFoundException(){
        super();
    }

    public PersonNotFoundException(String msg){
        super(msg);
    }
}
