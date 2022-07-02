package com.praise.io.productinventory.service;

import com.praise.io.productinventory.exception.ProductNotFoundException;
import com.praise.io.productinventory.model.Product;
import com.praise.io.productinventory.repository.ProductRepository;
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
    log.info("Saving new product: {}", product.getName());

    String randomSerialNumber = UUID.randomUUID().toString();
    product.setSerialNumber(randomSerialNumber);
    return productRepository.save(product);
  }

  @Override
  public List<Product> findAllProducts(int limit) {
    log.info("Fetching all products");
    PageRequest pageable = PageRequest.of(0, limit);
    return productRepository.findAll(pageable).toList();
  }

  public List<Product> findAllDeletedProducts() {
    log.info("Fetching all deleted products");
    return productRepository.findAllRecentlyDeletedProducts();
  }

  public Product updateProduct(Product product) {
    log.info("Updating product{}", product.getName());
    return productRepository.save(product);
  }

  public Product get(Long id) {
    log.info("Fetching product by id..");
    return productRepository
        .findById(id)
        .orElseThrow(() -> new ProductNotFoundException("Product with " + id + " does not exist"));
  }

  public Boolean delete(Long id) {
    log.info("Deleting server by id {}", id);
    productRepository.deleteById(id);
    return Boolean.TRUE;
  }
}
