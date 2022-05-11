package com.praise.io.shopifychallenge2022.enumertation;

import lombok.Getter;

@Getter
public enum ProductCategories {
  ELECTRONICS("ELECTRONICS"),
  BOOKS("BOOKS"),
  CLOTHES("CLOTHES"),
  TOYS("TOYS");

  private final String value;

  ProductCategories(String value) {
    this.value = value;
  }
}
