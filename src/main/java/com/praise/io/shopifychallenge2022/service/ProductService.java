package com.praise.io.shopifychallenge2022.service;

import com.praise.io.shopifychallenge2022.model.Product;
import java.util.Collection;
import java.util.List;

public interface ProductService {

  Product createProduct(Product product);
  List<Product> findAllProducts(int limit);
  Product updateProduct(Product product);
  Boolean delete(Long id);
}
