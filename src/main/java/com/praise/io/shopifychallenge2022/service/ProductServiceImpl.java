package com.praise.io.shopifychallenge2022.service;

import com.praise.io.shopifychallenge2022.exception.ProductNotFoundException;
import com.praise.io.shopifychallenge2022.model.Product;
import com.praise.io.shopifychallenge2022.repository.ProductRepository;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Product createProduct(Product product) {
    String randomSerialNumber = UUID.randomUUID().toString();
    product.setSerialNumber(randomSerialNumber);
    log.info("Creating a product ");
    return productRepository.save(product);
  }

  @Override
  public List<Product> findAllProducts(int limit) {
    log.info("Retrieving all products");
    PageRequest pageable = PageRequest.of(0, limit);
    return productRepository.findAll(pageable).toList();
  }

  public List<Product> findAllDeletedProducts() {
    log.info("Retrieving all hidden products");
    return productRepository.findAllRecentlyDeletedProducts();
  }

  public Product updateProduct(Product product) {
    log.info("Updating product{}", product.getName());
    return productRepository.save(product);
  }

  public Product get(Long id) {
    log.info("Fetching product by id {}", id);
    return productRepository
        .findById(id)
        .orElseThrow(() -> new ProductNotFoundException("Product  does not exist"));
  }

  public Boolean delete(Long id) {
    productRepository.deleteById(id);
    log.info("Deleting product by id {}", id);
    return Boolean.TRUE;
  }
}
