package com.mydemo;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyPhone {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("com/mydemo/phonebean.xml");

        Phone phone = context.getBean("phone", Phone.class);
        System.out.println(phone.getOS());
        System.out.println(phone.getBrand());

        context.close();
    }
}
