package com.wholesalestore.wholesalestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.wholesalestore.wholesalestore.entities"})
public class WholesaleStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(WholesaleStoreApplication.class, args);
	}

}
