package com.praise.io.shopifychallenge2022.model;

import com.praise.io.shopifychallenge2022.enumertation.ProductCategories;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
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
  @Column(nullable = false, updatable = false, name = "id")
  private Long id;

  @NotEmpty(message = "Name must not be blank")
  @Column(name = "name")
  private String name;

  @Column(nullable = false, updatable = false, unique = true, name = "serial_number")
  private String serialNumber;

  @Range(min = 0,message = "quantity cannot be equals to or less than 0")
  @Column(name = "quantity")
  private Integer quantity;

  @NotBlank(message = "Category must be specified")
  @Column(name = "category")
  @Enumerated(value = EnumType.STRING)
  private ProductCategories productCategories;

  @Range(min = 0,message = "Price cannot be equals to or less than 0")
  @Column(name = "price")
  private BigDecimal price;

  @NotEmpty(message = "Image must be specified")
  @Column(name = "image_url")
  private String productImageUrl;

  // Lombok used here to avoid boilerplate code
}
