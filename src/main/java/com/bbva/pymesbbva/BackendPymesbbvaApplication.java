package com.bbva.pymesbbva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(/*exclude = { DataSourceAutoConfiguration.class }*/)
public class BackendPymesbbvaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendPymesbbvaApplication.class, args);
	}

}
