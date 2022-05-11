package com.praise.io.shopifychallenge2022;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryTrackingApplication.class, args);
	}

	//TODO Spring batch to read sql file into db upon restart
}
