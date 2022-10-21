package com.demo.springaop;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringaopApplication {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);

		ShoppingCart cart = context.getBean(ShoppingCart.class);
		cart.checkout();

	}

}
