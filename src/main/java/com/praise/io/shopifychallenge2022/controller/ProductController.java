package com.praise.io.shopifychallenge2022.controller;

import com.praise.io.shopifychallenge2022.model.Product;
import com.praise.io.shopifychallenge2022.model.Response;
import com.praise.io.shopifychallenge2022.service.ProductServiceImpl;
import java.time.LocalDateTime;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

  private static final int DEFAULT_PAGE_LIMIT = 30;
  private final ProductServiceImpl productService;

  @Autowired
  public ProductController(ProductServiceImpl productService) {
    this.productService = productService;
  }

  @GetMapping("/all")
  public ResponseEntity<Response> getAllProducts() {
    return ResponseEntity.ok(
        Response.builder()
            .timeStamp(LocalDateTime.now())
            .status(HttpStatus.OK)
            .data(Map.of("products", productService.findAllProducts(DEFAULT_PAGE_LIMIT)))
            .message("Products retrieved")
            .statusCode(HttpStatus.OK.value())
            .build());
  }

  @GetMapping("/deleted")
  public ResponseEntity<Response> getAllDeletedProducts() {
    return ResponseEntity.ok(
        Response.builder()
            .timeStamp(LocalDateTime.now())
            .status(HttpStatus.OK)
            .data(Map.of("products", productService.findAllSoftDeletedProduct()))
            .message("Recently deleted products")
            .statusCode(HttpStatus.OK.value())
            .build());
  }

  @PostMapping("/save")
  public ResponseEntity<Response> addProduct(@RequestBody @Valid Product product) {
    return ResponseEntity.ok(
        Response.builder()
            .timeStamp(LocalDateTime.now())
            .status(HttpStatus.CREATED)
            .data(Map.of("product", productService.createProduct(product)))
            .message("Product created")
            .statusCode(HttpStatus.CREATED.value())
            .build());
  }

  @PutMapping("/edit")
  public ResponseEntity<Response> updateProduct(@RequestBody @Valid Product product) {

    return ResponseEntity.ok(
        Response.builder()
            .timeStamp(LocalDateTime.now())
            .status(HttpStatus.OK)
            .data(Map.of("product", productService.updateProduct(product)))
            .message("Product updated")
            .statusCode(HttpStatus.OK.value())
            .build());
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Response> softDeleteProduct(@PathVariable("id") Long id) {

    return ResponseEntity.ok(
        Response.builder()
            .timeStamp(LocalDateTime.now())
            .status(HttpStatus.OK)
            .data(Map.of("product", productService.softDelete(id)))
            .message("Product deleted")
            .statusCode(HttpStatus.OK.value())
            .build());
  }

  @DeleteMapping("/remove/{id}")
  public ResponseEntity<Response> deleteProduct(@PathVariable("id") Long id) {

    return ResponseEntity.ok(
        Response.builder()
            .timeStamp(LocalDateTime.now())
            .status(HttpStatus.OK)
            .data(Map.of("product", productService.delete(id)))
            .message("Product deleted")
            .statusCode(HttpStatus.OK.value())
            .build());
  }

  @PostMapping("/restore")
  public ResponseEntity<Response> restoreSoftDeleteProduct(@RequestBody @Valid Product product) {
    return ResponseEntity.ok(
        Response.builder()
            .timeStamp(LocalDateTime.now())
            .status(HttpStatus.OK)
            .data(Map.of("product", productService.restoreSoftDelete(product)))
            .message("Product restored")
            .statusCode(HttpStatus.OK.value())
            .build());
  }
}