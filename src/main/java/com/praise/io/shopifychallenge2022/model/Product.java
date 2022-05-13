package com.praise.io.shopifychallenge2022.model;

import com.praise.io.shopifychallenge2022.enumertation.ProductCategories;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
public class Product implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false, updatable = false)
  private Long id;

  @NotEmpty(message = "Name must not be blank")
  private String name;

  @Column(nullable = false, updatable = false)
  private String serialNumber;

  @Range(min = 0, message = "quantity cannot be equals to or less than 0")
  private Integer quantity;

  @NotBlank(message = "Category must be specified")
  @Enumerated(EnumType.STRING)
  @Column(length = 10000)
  private ProductCategories category;

  @Range(min = 0, message = "Price cannot be equals to or less than 0")
  private BigDecimal price;

  @NotEmpty(message = "Image must be specified")
  private String imageUrl;

  // Lombok used here to avoid boilerplate code
}
