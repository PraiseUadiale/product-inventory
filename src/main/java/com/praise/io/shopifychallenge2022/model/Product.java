package com.praise.io.shopifychallenge2022.model;

import com.praise.io.shopifychallenge2022.enumeration.ProductCategories;
import com.praise.io.shopifychallenge2022.enumeration.ProductCategoryConverter;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false)
  private Long id;

  @NotEmpty(message = "Name must not be blank")
  private String name;

  @Column(nullable = false, updatable = false)
  private String serialNumber;

  @Range(min = 0, message = "quantity cannot be equals to or less than 0")
  private Integer quantity;

  @NotNull
  @Convert(converter = ProductCategoryConverter.class)
  private ProductCategories category;

  @Range(min = 0, message = "Price cannot be equals to or less than 0")
  private BigDecimal price;

  private Boolean isDeleted = Boolean.FALSE;

  // Lombok used here to avoid boilerplate code
}
