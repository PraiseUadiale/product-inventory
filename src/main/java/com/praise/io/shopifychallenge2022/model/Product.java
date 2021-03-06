package com.praise.io.shopifychallenge2022.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false)
  private Long id;

  @NotEmpty(message = "Name must not be blank")
  private String name;

  @Column(nullable = false, updatable = false)
  private String serialNumber;

  @Range(min = 0, message = "quantity cannot be equals to or less than 0")
  private Integer quantity;

  @NotEmpty(message = "Category must not be blank")
  private String category;

  @Range(min = 0, message = "Price cannot be equals to or less than 0")
  private BigDecimal price;

  private String comment;

  private Boolean isDeleted = Boolean.FALSE;

  // Lombok used here to avoid boilerplate code
}
