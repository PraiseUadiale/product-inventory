package com.praise.io.shopifychallenge2022.enumeration;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


public class ProductCategoryConverter implements AttributeConverter<ProductCategories, String> {

  @Override
  public String convertToDatabaseColumn(ProductCategories productCategories) {
    if (productCategories == null) {
      return null;
    }
    return productCategories.getCode();
  }

  @Override
  public ProductCategories convertToEntityAttribute(String input) {
    if (input == null) {
      return null;
    }
    return ProductCategories.parseCategories(input);
  }
}
