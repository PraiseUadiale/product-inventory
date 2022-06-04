package com.praise.io.shopifychallenge2022.controller;

import com.praise.io.shopifychallenge2022.model.Product;
import com.praise.io.shopifychallenge2022.service.ProductService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

  private static final int DEFAULT_PAGE_LIMIT = 30;
  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/")
  public String getAllProducts(Model model) {
    model.addAttribute("allProducts", productService.findAllProducts(DEFAULT_PAGE_LIMIT));
    return "index";
  }

  @GetMapping("/deleted")
  public String getAllDeletedProducts(Model model) {
    model.addAttribute("deletedProducts", productService.findAllDeletedProducts());

    return "deleted_product";
  }

  @GetMapping("/product/new")
  public String createProductForm(Model model) {

    model.addAttribute("newProduct", new Product());
    return "add_product";
  }

  @PostMapping("/save")
  public String addProduct(@ModelAttribute @Valid Product product) {
    productService.createProduct(product);
    return "redirect:/";
  }

  @GetMapping("/product/edit/{id}")
  public String editProductForm(@PathVariable("id") Long id, Model model) {
    model.addAttribute("editProduct", productService.get(id));
    return "edit_product";
  }

  @PostMapping("/edit/{id}")
  public String updateProduct(@Valid Product product, @PathVariable("id") Long id) {
    Product existingProduct = productService.get(id);
    existingProduct.setName(product.getName());
    existingProduct.setPrice(product.getPrice());
    existingProduct.setQuantity(product.getQuantity());
    existingProduct.setCategory(product.getCategory());
    productService.updateProduct(existingProduct);
    System.out.println(existingProduct);
    return "redirect:/";
  }

  @GetMapping("/delete/{id}")
  public String deleteProduct(@PathVariable("id") Long id) {
    productService.delete(id);
    return "redirect:/";
  }

  @GetMapping("/edit/partial/{id}")
  public String partialUpdateProduct(
      @PathVariable("id") Long id,
      @RequestParam(value = "comment", required = false) String comment) {
    Product productFromDB = productService.get(id);
    boolean isDeleted = productFromDB.getIsDeleted();
    productFromDB.setIsDeleted(!isDeleted);
    productFromDB.setComment(comment);
    productService.updateProduct(productFromDB);
    return "redirect:/";
  }
}
