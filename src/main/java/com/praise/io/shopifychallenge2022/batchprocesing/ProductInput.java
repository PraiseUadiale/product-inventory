package com.praise.io.shopifychallenge2022.batchprocesing;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInput {

  private String id;
  private String name;
  private String serialNumber;
  private String quantity;
  private String category;
  private String price;
  private String imageUrl;
}
