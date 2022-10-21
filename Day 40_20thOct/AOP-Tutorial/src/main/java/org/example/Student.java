package org.example;

import org.springframework.stereotype.Component;

@Component
public class Student {
    public void study(){
        new Human().wakeUp();
        System.out.println("I am studying right now!");
    }
}
