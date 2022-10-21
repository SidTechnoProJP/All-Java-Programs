package com.springaop.demo;

import org.springframework.stereotype.Component;

@Component
public class Student {
    public int studyNone(int x, int y){
        //new Human().wakeUp();
        System.out.println("I am studying right now!");
        return 0;
    }
}
