package com.huyen.inventory_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryManagementApplication {

	public static void main(String[] args) {
        SpringApplication.run(InventoryManagementApplication.class, args);
        // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // System.out.println("Encoded password: " + encoder.encode("admin"));
	}

}
