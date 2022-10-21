package com.springaop.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class App {
	public static void main(String[] args) {

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		Student student = context.getBean("student", Student.class);

		//joinpoint (before)
		student.studyNone(45,55);
		//joinpoint (after)

		Employee employee = context.getBean("employee", Employee.class);

		//joinpoint (before)
		employee.studySome();
		//joinpoint (after)
	}
}
