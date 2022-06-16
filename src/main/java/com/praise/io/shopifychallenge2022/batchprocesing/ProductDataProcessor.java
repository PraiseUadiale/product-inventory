package com.praise.io.shopifychallenge2022.batchprocesing;

import com.praise.io.shopifychallenge2022.enumeration.ProductCategories;
import com.praise.io.shopifychallenge2022.model.Product;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class ProductDataProcessor implements ItemProcessor<ProductInput, Product> {

  @Override
  public Product process(ProductInput productInput) throws Exception {

    Product product = new Product();
    product.setId(Long.parseLong(productInput.getId()));
    product.setName(productInput.getName());
    product.setSerialNumber(productInput.getSerialNumber());
    product.setQuantity(Integer.parseInt(productInput.getQuantity()));
    ProductCategories category = ProductCategories.parseCategories(productInput.getCategory());
    product.setCategory(category);
    product.setPrice(BigDecimal.valueOf(Long.parseLong(productInput.getPrice())));

    log.info("Converting (" + productInput + ") into (" + product + ")");
    return product;
  }
}
