package com.praise.io.shopifychallenge2022.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.praise.io.shopifychallenge2022.model.Product;
import com.praise.io.shopifychallenge2022.model.Response;
import com.praise.io.shopifychallenge2022.repository.ProductRepository;
import com.praise.io.shopifychallenge2022.service.ProductServiceImpl;
import java.math.BigDecimal;
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
    product.setImageUrl("https://example.org/example");
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
    product1.setImageUrl("https://example.org/example");
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
  void testAddProduct2() {
    Product product = new Product();
    product.setQuantity(1);
    product.setIsDeleted(true);
    product.setId(123L);
    product.setImageUrl("https://example.org/example");
    product.setName("Name");
    product.setCategory("Category");
    product.setPrice(BigDecimal.valueOf(42L));
    product.setSerialNumber("42");
    ProductServiceImpl productServiceImpl = mock(ProductServiceImpl.class);
    when(productServiceImpl.createProduct((Product) any())).thenReturn(product);
    ProductController productController = new ProductController(productServiceImpl);

    Product product1 = new Product();
    product1.setQuantity(1);
    product1.setIsDeleted(true);
    product1.setId(123L);
    product1.setImageUrl("https://example.org/example");
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
    verify(productServiceImpl).createProduct((Product) any());
  }

  @Test
  void testUpdateProduct() {
    Product product = new Product();
    product.setQuantity(1);
    product.setIsDeleted(true);
    product.setId(123L);
    product.setImageUrl("https://example.org/example");
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
    product1.setImageUrl("https://example.org/example");
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
  void testUpdateProduct2() {
    Product product = new Product();
    product.setQuantity(1);
    product.setIsDeleted(true);
    product.setId(123L);
    product.setImageUrl("https://example.org/example");
    product.setName("Name");
    product.setCategory("Category");
    product.setPrice(BigDecimal.valueOf(42L));
    product.setSerialNumber("42");
    ProductServiceImpl productServiceImpl = mock(ProductServiceImpl.class);
    when(productServiceImpl.updateProduct((Product) any())).thenReturn(product);
    ProductController productController = new ProductController(productServiceImpl);

    Product product1 = new Product();
    product1.setQuantity(1);
    product1.setIsDeleted(true);
    product1.setId(123L);
    product1.setImageUrl("https://example.org/example");
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
    verify(productServiceImpl).updateProduct((Product) any());
  }

  @Test
  void testSoftDeleteProduct() {
    ProductRepository productRepository = mock(ProductRepository.class);
    doNothing().when(productRepository).softDelete((Boolean) any(), (Long) any());
    ResponseEntity<Response> actualSoftDeleteProductResult = (new ProductController(
        new ProductServiceImpl(productRepository))).softDeleteProduct(123L);
    assertTrue(actualSoftDeleteProductResult.getHeaders().isEmpty());
    assertTrue(actualSoftDeleteProductResult.hasBody());
    assertEquals(HttpStatus.OK, actualSoftDeleteProductResult.getStatusCode());
    Response body = actualSoftDeleteProductResult.getBody();
    assertNull(body.getReason());
    assertEquals("Product deleted", body.getMessage());
    assertNull(body.getDeveloperMessage());
    assertEquals(1, body.getData().size());
    assertEquals(HttpStatus.OK, body.getStatus());
    assertEquals(200, body.getStatusCode());
    verify(productRepository).softDelete((Boolean) any(), (Long) any());
  }

  @Test
  void testSoftDeleteProduct2() {
    ProductServiceImpl productServiceImpl = mock(ProductServiceImpl.class);
    when(productServiceImpl.softDelete((Long) any())).thenReturn(true);
    ResponseEntity<Response> actualSoftDeleteProductResult = (new ProductController(
        productServiceImpl))
        .softDeleteProduct(123L);
    assertTrue(actualSoftDeleteProductResult.getHeaders().isEmpty());
    assertTrue(actualSoftDeleteProductResult.hasBody());
    assertEquals(HttpStatus.OK, actualSoftDeleteProductResult.getStatusCode());
    Response body = actualSoftDeleteProductResult.getBody();
    assertNull(body.getReason());
    assertEquals("Product deleted", body.getMessage());
    assertNull(body.getDeveloperMessage());
    assertEquals(1, body.getData().size());
    assertEquals(HttpStatus.OK, body.getStatus());
    assertEquals(200, body.getStatusCode());
    verify(productServiceImpl).softDelete((Long) any());
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

  @Test
  void testDeleteProduct2() {
    ProductServiceImpl productServiceImpl = mock(ProductServiceImpl.class);
    when(productServiceImpl.delete((Long) any())).thenReturn(true);
    ResponseEntity<Response> actualDeleteProductResult = (new ProductController(productServiceImpl))
        .deleteProduct(123L);
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
    verify(productServiceImpl).delete((Long) any());
  }

  @Test
  void testRestoreSoftDeleteProduct() {
    Product product = new Product();
    product.setQuantity(1);
    product.setIsDeleted(true);
    product.setId(123L);
    product.setImageUrl("https://example.org/example");
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
    product1.setImageUrl("https://example.org/example");
    product1.setName("Name");
    product1.setCategory("Category");
    product1.setPrice(BigDecimal.valueOf(42L));
    product1.setSerialNumber("42");
    ResponseEntity<Response> actualRestoreSoftDeleteProductResult = productController
        .restoreSoftDeleteProduct(product1);
    assertTrue(actualRestoreSoftDeleteProductResult.getHeaders().isEmpty());
    assertTrue(actualRestoreSoftDeleteProductResult.hasBody());
    assertEquals(HttpStatus.OK, actualRestoreSoftDeleteProductResult.getStatusCode());
    Response body = actualRestoreSoftDeleteProductResult.getBody();
    assertNull(body.getReason());
    assertEquals("Product restored", body.getMessage());
    assertNull(body.getDeveloperMessage());
    assertEquals(1, body.getData().size());
    assertEquals(HttpStatus.OK, body.getStatus());
    assertEquals(200, body.getStatusCode());
    verify(productRepository).save((Product) any());
    assertFalse(product1.getIsDeleted());
  }

  @Test
  void testRestoreSoftDeleteProduct2() {
    Product product = new Product();
    product.setQuantity(1);
    product.setIsDeleted(true);
    product.setId(123L);
    product.setImageUrl("https://example.org/example");
    product.setName("Name");
    product.setCategory("Category");
    product.setPrice(BigDecimal.valueOf(42L));
    product.setSerialNumber("42");
    ProductServiceImpl productServiceImpl = mock(ProductServiceImpl.class);
    when(productServiceImpl.restoreSoftDelete((Product) any())).thenReturn(product);
    ProductController productController = new ProductController(productServiceImpl);

    Product product1 = new Product();
    product1.setQuantity(1);
    product1.setIsDeleted(true);
    product1.setId(123L);
    product1.setImageUrl("https://example.org/example");
    product1.setName("Name");
    product1.setCategory("Category");
    product1.setPrice(BigDecimal.valueOf(42L));
    product1.setSerialNumber("42");
    ResponseEntity<Response> actualRestoreSoftDeleteProductResult = productController
        .restoreSoftDeleteProduct(product1);
    assertTrue(actualRestoreSoftDeleteProductResult.getHeaders().isEmpty());
    assertTrue(actualRestoreSoftDeleteProductResult.hasBody());
    assertEquals(HttpStatus.OK, actualRestoreSoftDeleteProductResult.getStatusCode());
    Response body = actualRestoreSoftDeleteProductResult.getBody();
    assertNull(body.getReason());
    assertEquals("Product restored", body.getMessage());
    assertNull(body.getDeveloperMessage());
    assertEquals(1, body.getData().size());
    assertEquals(HttpStatus.OK, body.getStatus());
    assertEquals(200, body.getStatusCode());
    verify(productServiceImpl).restoreSoftDelete((Product) any());
  }
}

