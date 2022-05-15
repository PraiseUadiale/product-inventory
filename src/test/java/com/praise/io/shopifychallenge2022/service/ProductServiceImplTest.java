package com.praise.io.shopifychallenge2022.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.praise.io.shopifychallenge2022.model.Product;
import com.praise.io.shopifychallenge2022.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {

  @MockBean
  private ProductRepository productRepository;

  @Autowired
  private ProductServiceImpl productServiceImpl;

  @Test
  void testCreateProduct() {
    Product product = new Product();
    product.setQuantity(1);
    product.setIsDeleted(true);
    product.setId(123L);
    product.setImageUrl("https://example.org/example");
    product.setName("Name");
    product.setCategory("Category");
    product.setPrice(BigDecimal.valueOf(42L));
    product.setSerialNumber("42");
    when(this.productRepository.save((Product) any())).thenReturn(product);

    Product product1 = new Product();
    product1.setQuantity(1);
    product1.setIsDeleted(true);
    product1.setId(123L);
    product1.setImageUrl("https://example.org/example");
    product1.setName("Name");
    product1.setCategory("Category");
    product1.setPrice(BigDecimal.valueOf(42L));
    product1.setSerialNumber("42");
    assertSame(product, this.productServiceImpl.createProduct(product1));
    verify(this.productRepository).save((Product) any());
    assertTrue(this.productServiceImpl.findAllDeletedProducts().isEmpty());
  }

  @Test
  void testFindAllDeletedProducts() {
    when(this.productRepository.findAllRecentlyDeletedProducts()).thenReturn(null);
    assertNull(this.productServiceImpl.findAllDeletedProducts());
    verify(this.productRepository).findAllRecentlyDeletedProducts();
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
    when(this.productRepository.save((Product) any())).thenReturn(product);

    Product product1 = new Product();
    product1.setQuantity(1);
    product1.setIsDeleted(true);
    product1.setId(123L);
    product1.setImageUrl("https://example.org/example");
    product1.setName("Name");
    product1.setCategory("Category");
    product1.setPrice(BigDecimal.valueOf(42L));
    product1.setSerialNumber("42");
    assertSame(product, this.productServiceImpl.updateProduct(product1));
    verify(this.productRepository).save((Product) any());
    assertTrue(this.productServiceImpl.findAllDeletedProducts().isEmpty());
  }

  @Test
  void testGet() {
    Product product = new Product();
    product.setQuantity(1);
    product.setIsDeleted(true);
    product.setId(123L);
    product.setImageUrl("https://example.org/example");
    product.setName("Name");
    product.setCategory("Category");
    product.setPrice(BigDecimal.valueOf(42L));
    product.setSerialNumber("42");
    Optional<Product> ofResult = Optional.of(product);
    when(this.productRepository.findById((Long) any())).thenReturn(ofResult);
    assertSame(product, this.productServiceImpl.get(123L));
    verify(this.productRepository).findById((Long) any());
    assertTrue(this.productServiceImpl.findAllDeletedProducts().isEmpty());
  }

  @Test
  void testDelete() {
    doNothing().when(this.productRepository).deleteById((Long) any());
    assertTrue(this.productServiceImpl.delete(123L));
    verify(this.productRepository).deleteById((Long) any());
    assertTrue(this.productServiceImpl.findAllDeletedProducts().isEmpty());
  }
}

