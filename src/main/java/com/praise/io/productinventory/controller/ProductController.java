package com.praise.io.productinventory.controller;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.praise.io.productinventory.model.Product;
import com.praise.io.productinventory.model.ClientResponsePayload;
import com.praise.io.productinventory.service.ProductService;
import java.time.LocalDateTime;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/")
  public ResponseEntity<ClientResponsePayload> getAllProducts() {
    return ResponseEntity.ok(
        ClientResponsePayload.builder()
            .timeStamp(LocalDateTime.now())
            .status(HttpStatus.OK)
            .data(Map.of("products", productService.findAllProducts(DEFAULT_PAGE_LIMIT)))
            .message("Products retrieved")
            .statusCode(HttpStatus.OK.value())
            .build());
  }

  @GetMapping("/deletedProduct")
  public ResponseEntity<ClientResponsePayload> getAllDeletedProducts() {
    return ResponseEntity.ok(
        ClientResponsePayload.builder()
            .timeStamp(LocalDateTime.now())
            .status(HttpStatus.OK)
            .data(Map.of("products", productService.findAllDeletedProducts()))
            .message("Recently deleted products")
            .statusCode(HttpStatus.OK.value())
            .build());
  }

  @PostMapping("/save")
  public ResponseEntity<ClientResponsePayload> addProduct(@RequestBody @Valid Product product) {
    return ResponseEntity.ok(
        ClientResponsePayload.builder()
            .timeStamp(LocalDateTime.now())
            .status(HttpStatus.CREATED)
            .data(Map.of("product", productService.createProduct(product)))
            .message("Product created")
            .statusCode(HttpStatus.CREATED.value())
            .build());
  }

  @GetMapping("/user")
  public String loggedInUser(@AuthenticationPrincipal OAuth2User principal) {
    System.out.println(principal);
    return principal.getAttribute("name");
  }

  @PutMapping("/edit")
  public ResponseEntity<ClientResponsePayload> updateProduct(@RequestBody @Valid Product product) {

    return ResponseEntity.ok(
        ClientResponsePayload.builder()
            .timeStamp(LocalDateTime.now())
            .status(HttpStatus.OK)
            .data(Map.of("product", productService.updateProduct(product)))
            .message("Product updated")
            .statusCode(HttpStatus.OK.value())
            .build());
  }

  @PatchMapping("/edit/update/")
  public ResponseEntity<ClientResponsePayload> partialUpdateProduct(@RequestBody ObjectNode objectNode) {
    Long id = objectNode.get("id").asLong();
    Boolean isDeleted = objectNode.get("isDeleted").asBoolean();
    Product productFromDB = productService.get(id);
    productFromDB.setIsDeleted(isDeleted);

    return ResponseEntity.ok(
        ClientResponsePayload.builder()
            .timeStamp(LocalDateTime.now())
            .status(HttpStatus.OK)
            .data(Map.of("product", productService.updateProduct(productFromDB)))
            .message("Product Updated")
            .statusCode(HttpStatus.OK.value())
            .build());
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<ClientResponsePayload> deleteProduct(@PathVariable("id") Long id) {

    return ResponseEntity.ok(
        ClientResponsePayload.builder()
            .timeStamp(LocalDateTime.now())
            .status(HttpStatus.OK)
            .data(Map.of("product", productService.delete(id)))
            .message("Product deleted")
            .statusCode(HttpStatus.OK.value())
            .build());
  }
}
