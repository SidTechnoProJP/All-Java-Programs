package com.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringApp {

    public static void main(String[] args) {

// load the spring configuration file
        ClassPathXmlApplicationContext context = new
                ClassPathXmlApplicationContext("com/springdemo/applicationContext.xml");
// retrieve bean from spring container
        Coach theCoach = context.getBean("myCoach", Coach.class);
//        Coach theCoach2 = context.getBean("myCoach2", Coach.class);

// call methods on the bean
        System.out.println(theCoach.getDailyWorkout());
//        System.out.println(theCoach2.getDailyWorkout());
// close the context
        context.close();
    }
}

