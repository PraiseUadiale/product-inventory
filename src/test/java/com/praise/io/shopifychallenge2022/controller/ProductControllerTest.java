package com.praise.io.shopifychallenge2022.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.praise.io.shopifychallenge2022.model.Product;
import com.praise.io.shopifychallenge2022.model.Response;
import com.praise.io.shopifychallenge2022.repository.ProductRepository;
import com.praise.io.shopifychallenge2022.service.ProductServiceImpl;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ProductControllerTest {

  @Test
  void testAddProduct() {
    Product product = new Product();
    product.setQuantity(1);
    product.setIsDeleted(true);
    product.setId(123L);
    product.setName("Name");
    product.setCategory("Category");
    product.setPrice(BigDecimal.valueOf(42L));
    product.setSerialNumber("42");
    ProductRepository productRepository = mock(ProductRepository.class);
    when(productRepository.save((Product) any())).thenReturn(product);
    ProductController productController = new ProductController(
        new ProductServiceImpl(productRepository));

    Product product1 = new Product();
    product1.setQuantity(1);
    product1.setIsDeleted(true);
    product1.setId(123L);
    product1.setName("Name");
    product1.setCategory("Category");
    product1.setPrice(BigDecimal.valueOf(42L));
    product1.setSerialNumber("42");
    ResponseEntity<Response> actualAddProductResult = productController.addProduct(product1);
    assertTrue(actualAddProductResult.getHeaders().isEmpty());
    assertTrue(actualAddProductResult.hasBody());
    assertEquals(HttpStatus.OK, actualAddProductResult.getStatusCode());
    Response body = actualAddProductResult.getBody();
    assertNull(body.getReason());
    assertEquals("Product created", body.getMessage());
    assertNull(body.getDeveloperMessage());
    assertEquals(1, body.getData().size());
    assertEquals(HttpStatus.CREATED, body.getStatus());
    assertEquals(201, body.getStatusCode());
    verify(productRepository).save((Product) any());
  }

  @Test
  void testUpdateProduct() {
    Product product = new Product();
    product.setQuantity(1);
    product.setIsDeleted(true);
    product.setId(123L);
    product.setName("Name");
    product.setCategory("Category");
    product.setPrice(BigDecimal.valueOf(42L));
    product.setSerialNumber("42");
    ProductRepository productRepository = mock(ProductRepository.class);
    when(productRepository.save((Product) any())).thenReturn(product);
    ProductController productController = new ProductController(
        new ProductServiceImpl(productRepository));

    Product product1 = new Product();
    product1.setQuantity(1);
    product1.setIsDeleted(true);
    product1.setId(123L);
    product1.setName("Name");
    product1.setCategory("Category");
    product1.setPrice(BigDecimal.valueOf(42L));
    product1.setSerialNumber("42");
    ResponseEntity<Response> actualUpdateProductResult = productController.updateProduct(product1);
    assertTrue(actualUpdateProductResult.getHeaders().isEmpty());
    assertTrue(actualUpdateProductResult.hasBody());
    assertEquals(HttpStatus.OK, actualUpdateProductResult.getStatusCode());
    Response body = actualUpdateProductResult.getBody();
    assertNull(body.getReason());
    assertEquals("Product updated", body.getMessage());
    assertNull(body.getDeveloperMessage());
    assertEquals(1, body.getData().size());
    assertEquals(HttpStatus.OK, body.getStatus());
    assertEquals(200, body.getStatusCode());
    verify(productRepository).save((Product) any());
  }

  @Test
  void testPartialUpdateProduct() {
    Product product = new Product();
    product.setQuantity(1);
    product.setIsDeleted(true);
    product.setId(123L);
    product.setName("Name");
    product.setCategory("Category");
    product.setPrice(BigDecimal.valueOf(42L));
    product.setSerialNumber("42");
    Optional<Product> ofResult = Optional.of(product);

    Product product1 = new Product();
    product1.setQuantity(1);
    product1.setIsDeleted(true);
    product1.setId(123L);
    product1.setName("Name");
    product1.setCategory("Category");
    product1.setPrice(BigDecimal.valueOf(42L));
    product1.setSerialNumber("42");
    ProductRepository productRepository = mock(ProductRepository.class);
    when(productRepository.save((Product) any())).thenReturn(product1);
    when(productRepository.findById((Long) any())).thenReturn(ofResult);
    ProductController productController = new ProductController(
        new ProductServiceImpl(productRepository));
    ObjectNode objectNode = mock(ObjectNode.class);
    when(objectNode.get((String) any())).thenReturn(DoubleNode.valueOf(10.0));
    ResponseEntity<Response> actualPartialUpdateProductResult = productController
        .partialUpdateProduct(objectNode);
    assertTrue(actualPartialUpdateProductResult.getHeaders().isEmpty());
    assertTrue(actualPartialUpdateProductResult.hasBody());
    assertEquals(HttpStatus.OK, actualPartialUpdateProductResult.getStatusCode());
    Response body = actualPartialUpdateProductResult.getBody();
    assertNull(body.getReason());
    assertEquals("Product Updated", body.getMessage());
    assertNull(body.getDeveloperMessage());
    assertEquals(1, body.getData().size());
    assertEquals(HttpStatus.OK, body.getStatus());
    assertEquals(200, body.getStatusCode());
    verify(productRepository).findById((Long) any());
    verify(productRepository).save((Product) any());
    verify(objectNode, atLeast(1)).get((String) any());
  }

  @Test
  void testPartialUpdateProduct2() {
    Product product = new Product();
    product.setQuantity(1);
    product.setIsDeleted(true);
    product.setId(123L);
    product.setName("Name");
    product.setCategory("Category");
    product.setPrice(BigDecimal.valueOf(42L));
    product.setSerialNumber("42");
    Optional<Product> ofResult = Optional.of(product);

    Product product1 = new Product();
    product1.setQuantity(1);
    product1.setIsDeleted(true);
    product1.setId(123L);
    product1.setName("Name");
    product1.setCategory("Category");
    product1.setPrice(BigDecimal.valueOf(42L));
    product1.setSerialNumber("42");
    ProductRepository productRepository = mock(ProductRepository.class);
    when(productRepository.save((Product) any())).thenReturn(product1);
    when(productRepository.findById((Long) any())).thenReturn(ofResult);
    ProductController productController = new ProductController(
        new ProductServiceImpl(productRepository));
    ObjectNode objectNode = mock(ObjectNode.class);
    when(objectNode.get((String) any())).thenReturn(IntNode.valueOf(1));
    ResponseEntity<Response> actualPartialUpdateProductResult = productController
        .partialUpdateProduct(objectNode);
    assertTrue(actualPartialUpdateProductResult.getHeaders().isEmpty());
    assertTrue(actualPartialUpdateProductResult.hasBody());
    assertEquals(HttpStatus.OK, actualPartialUpdateProductResult.getStatusCode());
    Response body = actualPartialUpdateProductResult.getBody();
    assertNull(body.getReason());
    assertEquals("Product Updated", body.getMessage());
    assertNull(body.getDeveloperMessage());
    assertEquals(1, body.getData().size());
    assertEquals(HttpStatus.OK, body.getStatus());
    assertEquals(200, body.getStatusCode());
    verify(productRepository).findById((Long) any());
    verify(productRepository).save((Product) any());
    verify(objectNode, atLeast(1)).get((String) any());
  }

  @Test
  void testDeleteProduct() {
    ProductRepository productRepository = mock(ProductRepository.class);
    doNothing().when(productRepository).deleteById((Long) any());
    ResponseEntity<Response> actualDeleteProductResult = (new ProductController(
        new ProductServiceImpl(productRepository))).deleteProduct(123L);
    assertTrue(actualDeleteProductResult.getHeaders().isEmpty());
    assertTrue(actualDeleteProductResult.hasBody());
    assertEquals(HttpStatus.OK, actualDeleteProductResult.getStatusCode());
    Response body = actualDeleteProductResult.getBody();
    assertNull(body.getReason());
    assertEquals("Product deleted", body.getMessage());
    assertNull(body.getDeveloperMessage());
    assertEquals(1, body.getData().size());
    assertEquals(HttpStatus.OK, body.getStatus());
    assertEquals(200, body.getStatusCode());
    verify(productRepository).deleteById((Long) any());
  }
}

