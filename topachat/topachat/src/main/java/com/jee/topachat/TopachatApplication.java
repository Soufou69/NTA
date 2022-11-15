package com.jee.topachat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class TopachatApplication {

	public static void main(String[] args) {

		SpringApplication.run(TopachatApplication.class, args);
	}

}
