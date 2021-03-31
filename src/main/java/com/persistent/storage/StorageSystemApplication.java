package com.persistent.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class StorageSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorageSystemApplication.class, args);
	}

}
