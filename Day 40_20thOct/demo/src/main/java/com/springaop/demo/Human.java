package com.springaop.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class Human {

   // @After("execution(public void study*())") all functions that contain keyword study
   // @After("execution(public * study*())") //all functions with all return types and all keywords containing study



    //@After("execution(public void com.springaop.demo.Employee.study())")
    //@Before("execution(public void study())")

    @Before("myPointCuts()")
    public void wakeUp(JoinPoint j){
//        System.out.println("Shubh Sakal"+j.getArgs()[0]);
        System.out.println("Shubh Sakal"+j.getSignature());
    }

    @After("myPointCuts()")
    public void sleep(){
        System.out.println("Shubh Raatri");
    }

    @Pointcut("execution(public * study*(..))")
    public void myPointCuts(){}

}