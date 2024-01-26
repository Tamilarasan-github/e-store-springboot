package com.tamilcreations.estorespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@EnableAspectJAutoProxy //Working fine with & without this
@SpringBootApplication
public class EStoreSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(EStoreSpringbootApplication.class, args);
	}

}
