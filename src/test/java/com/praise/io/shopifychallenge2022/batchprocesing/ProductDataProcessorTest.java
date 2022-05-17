package com.praise.io.shopifychallenge2022.batchprocesing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.praise.io.shopifychallenge2022.model.Product;
import org.junit.jupiter.api.Test;

class ProductDataProcessorTest {

  @Test
  void testProcess() throws Exception {
    ProductDataProcessor productDataProcessor = new ProductDataProcessor();

    ProductInput productInput = new ProductInput();
    productInput.setId("42");
    productInput.setName("Name");
    productInput.setCategory("Category");
    productInput.setQuantity("42");
    productInput.setSerialNumber("42");
    productInput.setPrice("42");
    Product actualProcessResult = productDataProcessor.process(productInput);
    assertEquals("Category", actualProcessResult.getCategory());
    assertEquals(
        "Product(id=42, name=Name, serialNumber=42, quantity=42, category=Category, price=42, comment=null,"
            + " isDeleted=false)", actualProcessResult.toString());
    assertEquals("42", actualProcessResult.getSerialNumber());
    assertEquals(42, actualProcessResult.getQuantity().intValue());
    assertEquals("Name", actualProcessResult.getName());
    assertFalse(actualProcessResult.getIsDeleted());
    assertEquals(42L, actualProcessResult.getId().longValue());
  }
}

