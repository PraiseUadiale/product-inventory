package com.praise.io.shopifychallenge2022;

import com.praise.io.shopifychallenge2022.enumeration.ProductCategories;
import com.praise.io.shopifychallenge2022.model.Product;
import com.praise.io.shopifychallenge2022.service.ProductService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryTrackingApplication {

  public static void main(String[] args) {
    SpringApplication.run(InventoryTrackingApplication.class, args);
  }
}
