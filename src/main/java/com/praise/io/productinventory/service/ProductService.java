package com.praise.io.productinventory.service;

import com.praise.io.productinventory.model.Product;
import java.util.List;

public interface ProductService {

  Product createProduct(Product product);

  List<Product> findAllProducts(int limit);

  Product updateProduct(Product product);

  Boolean delete(Long id);

  List<Product> findAllDeletedProducts();

  Product get(Long id);
}
