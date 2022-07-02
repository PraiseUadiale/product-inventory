package com.praise.io.productinventory.batchprocesing;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductInput {

  private String id;
  private String name;
  private String serialNumber;
  private String quantity;
  private String category;
  private String price;
}
