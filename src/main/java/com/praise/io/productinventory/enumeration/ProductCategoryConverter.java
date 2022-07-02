package com.praise.io.productinventory.enumeration;

import javax.persistence.AttributeConverter;


public class ProductCategoryConverter implements AttributeConverter<ProductCategories, String> {

  @Override
  public String convertToDatabaseColumn(ProductCategories productCategories) {
    if (productCategories == null) {
      return null;
    }
    return productCategories.toString();
  }

  @Override
  public ProductCategories convertToEntityAttribute(String input) {
    if (input == null) {
      return null;
    }
    return ProductCategories.parseCategories(input);
  }
}
