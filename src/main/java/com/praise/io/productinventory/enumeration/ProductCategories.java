package com.praise.io.productinventory.enumeration;

import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum ProductCategories {
  ELECTRONICS,
  BOOKS,
  CLOTHES,
  INDUSTRIAL,
  JEWELLERY;

  public static ProductCategories parseCategories(String input) {

    return Stream.of(values())
        .filter(p -> p.toString().equals(input))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
