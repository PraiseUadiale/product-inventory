package com.praise.io.shopifychallenge2022.batchprocesing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ProductInputTest {

  @Test
  void testConstructor() {
    ProductInput actualProductInput = new ProductInput();
    actualProductInput.setCategory("Category");
    actualProductInput.setId("42");
    actualProductInput.setImageUrl("https://example.org/example");
    actualProductInput.setName("Name");
    actualProductInput.setPrice("Price");
    actualProductInput.setQuantity("Quantity");
    actualProductInput.setSerialNumber("42");
    assertEquals("Category", actualProductInput.getCategory());
    assertEquals("42", actualProductInput.getId());
    assertEquals("https://example.org/example", actualProductInput.getImageUrl());
    assertEquals("Name", actualProductInput.getName());
    assertEquals("Price", actualProductInput.getPrice());
    assertEquals("Quantity", actualProductInput.getQuantity());
    assertEquals("42", actualProductInput.getSerialNumber());
  }
}

