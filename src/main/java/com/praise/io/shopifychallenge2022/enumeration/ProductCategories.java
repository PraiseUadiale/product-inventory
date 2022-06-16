package com.praise.io.shopifychallenge2022.enumeration;

import java.util.Optional;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum ProductCategories {
  ELECTRONICS("E"),
  BOOKS("B"),
  CLOTHES("C"),
  INDUSTRIAL("I"),
  JEWELLERY("J");

  private final String code;

  ProductCategories(String code) {
    this.code = code;
  }

  public static ProductCategories parseCategories(String input) {

    return Stream.of(values())
        .filter(p -> p.toString().equals(input))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
