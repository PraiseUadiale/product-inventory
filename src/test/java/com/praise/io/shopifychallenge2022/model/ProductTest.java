package com.praise.io.shopifychallenge2022.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class ProductTest {

  @Test
  void testConstructor() {
    Product actualProduct = new Product();
    actualProduct.setCategory("Category");
    actualProduct.setComment("Comment");
    actualProduct.setId(123L);
    actualProduct.setIsDeleted(true);
    actualProduct.setName("Name");
    BigDecimal valueOfResult = BigDecimal.valueOf(42L);
    actualProduct.setPrice(valueOfResult);
    actualProduct.setQuantity(1);
    actualProduct.setSerialNumber("42");
    assertEquals("Category", actualProduct.getCategory());
    assertEquals("Comment", actualProduct.getComment());
    assertEquals(123L, actualProduct.getId().longValue());
    assertTrue(actualProduct.getIsDeleted());
    assertEquals("Name", actualProduct.getName());
    assertSame(valueOfResult, actualProduct.getPrice());
    assertEquals(1, actualProduct.getQuantity().intValue());
    assertEquals("42", actualProduct.getSerialNumber());
    assertEquals(
        "Product(id=123, name=Name, serialNumber=42, quantity=1, category=Category, price=42, comment=Comment,"
            + " isDeleted=true)", actualProduct.toString());
  }

  @Test
  void testConstructor2() {
    assertFalse((new Product()).getIsDeleted());
  }

  @Test
  void testConstructor3() {
    Product actualProduct = new Product(123L, "Name", "42", 1, "Category", BigDecimal.valueOf(42L),
        "Comment", true);

    assertEquals("Category", actualProduct.getCategory());
    assertEquals(
        "Product(id=123, name=Name, serialNumber=42, quantity=1, category=Category, price=42, comment=Comment,"
            + " isDeleted=true)", actualProduct.toString());
    assertEquals("42", actualProduct.getSerialNumber());
    assertEquals(1, actualProduct.getQuantity().intValue());
    assertEquals("Name", actualProduct.getName());
    assertTrue(actualProduct.getIsDeleted());
    assertEquals(123L, actualProduct.getId().longValue());
    assertEquals("Comment", actualProduct.getComment());
  }
}

