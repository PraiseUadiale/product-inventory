package com.praise.io.shopifychallenge2022.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.praise.io.shopifychallenge2022.model.Product;
import com.praise.io.shopifychallenge2022.service.ProductService;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
class ProductControllerTest {

  @Autowired
  private ProductController productController;

  @MockBean
  private ProductService productService;

  @Test
  void testAddProduct() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/save");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  @Test
  void testAddProduct5() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/save");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  @Test
  void testAddProduct6() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/save", "Uri Vars");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  @Test
  void testAddProduct7() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/save")
        .param("id", "42");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  @Test
  void testAddProduct8() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/save")
        .param("serialNumber", "42");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  @Test
  void testCreateProductForm3() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/new");
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(1))
        .andExpect(MockMvcResultMatchers.model().attributeExists("newProduct"))
        .andExpect(MockMvcResultMatchers.view().name("add_product"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("add_product"));
  }

  @Test
  void testCreateProductForm4() throws Exception {
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/product/new");
    getResult.contentType("Not all who wander are lost");
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(1))
        .andExpect(MockMvcResultMatchers.model().attributeExists("newProduct"))
        .andExpect(MockMvcResultMatchers.view().name("add_product"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("add_product"));
  }

  @Test
  void testDeleteProduct3() throws Exception {
    when(this.productService.delete((Long) any())).thenReturn(true);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/delete/{id}", 123L);
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isFound())
        .andExpect(MockMvcResultMatchers.model().size(0))
        .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
  }

  @Test
  void testDeleteProduct4() throws Exception {
    when(this.productService.delete((Long) any())).thenReturn(true);
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/delete/{id}", 123L);
    getResult.contentType("Not all who wander are lost");
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isFound())
        .andExpect(MockMvcResultMatchers.model().size(0))
        .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
  }

  @Test
  void testEditProductForm2() throws Exception {
    Product product = new Product();
    product.setQuantity(1);
    product.setIsDeleted(true);
    product.setId(123L);
    product.setName("Name");
    product.setCategory("Category");
    product.setPrice(BigDecimal.valueOf(42L));
    product.setSerialNumber("42");
    product.setComment("Comment");
    when(this.productService.get((Long) any())).thenReturn(product);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/product/edit/{id}", 123L);
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(1))
        .andExpect(MockMvcResultMatchers.model().attributeExists("editProduct"))
        .andExpect(MockMvcResultMatchers.view().name("edit_product"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("edit_product"));
  }

  @Test
  void testGetAllDeletedProducts3() throws Exception {
    when(this.productService.findAllDeletedProducts()).thenReturn(null);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/deleted");
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(1))
        .andExpect(MockMvcResultMatchers.view().name("deleted_product"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("deleted_product"));
  }

  @Test
  void testGetAllDeletedProducts4() throws Exception {
    when(this.productService.findAllDeletedProducts()).thenReturn(null);
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/deleted");
    getResult.contentType("Not all who wander are lost");
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(1))
        .andExpect(MockMvcResultMatchers.view().name("deleted_product"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("deleted_product"));
  }

  @Test
  void testGetAllProducts3() throws Exception {
    when(this.productService.findAllProducts(anyInt())).thenReturn(null);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(1))
        .andExpect(MockMvcResultMatchers.view().name("index"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("index"));
  }

  @Test
  void testGetAllProducts4() throws Exception {
    when(this.productService.findAllProducts(anyInt())).thenReturn(null);
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/");
    getResult.contentType("Not all who wander are lost");
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(1))
        .andExpect(MockMvcResultMatchers.view().name("index"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("index"));
  }

  @Test
  void testPartialUpdateProduct() throws Exception {
    Product product = new Product();
    product.setQuantity(1);
    product.setIsDeleted(true);
    product.setId(123L);
    product.setName("Name");
    product.setCategory("Category");
    product.setPrice(BigDecimal.valueOf(42L));
    product.setSerialNumber("42");
    product.setComment("Comment");

    Product product1 = new Product();
    product1.setQuantity(1);
    product1.setIsDeleted(true);
    product1.setId(123L);
    product1.setName("Name");
    product1.setCategory("Category");
    product1.setPrice(BigDecimal.valueOf(42L));
    product1.setSerialNumber("42");
    product1.setComment("Comment");
    when(this.productService.updateProduct((Product) any())).thenReturn(product1);
    when(this.productService.get((Long) any())).thenReturn(product);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/edit/partial/{id}", 123L);
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isFound())
        .andExpect(MockMvcResultMatchers.model().size(0))
        .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
  }

  @Test
  void testPartialUpdateProduct2() throws Exception {
    Product product = new Product();
    product.setQuantity(1);
    product.setIsDeleted(false);
    product.setId(123L);
    product.setName("Name");
    product.setCategory("Category");
    product.setPrice(BigDecimal.valueOf(42L));
    product.setSerialNumber("42");
    product.setComment("Comment");

    Product product1 = new Product();
    product1.setQuantity(1);
    product1.setIsDeleted(true);
    product1.setId(123L);
    product1.setName("Name");
    product1.setCategory("Category");
    product1.setPrice(BigDecimal.valueOf(42L));
    product1.setSerialNumber("42");
    product1.setComment("Comment");
    when(this.productService.updateProduct((Product) any())).thenReturn(product1);
    when(this.productService.get((Long) any())).thenReturn(product);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/edit/partial/{id}", 123L);
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isFound())
        .andExpect(MockMvcResultMatchers.model().size(0))
        .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
  }

  @Test
  void testPartialUpdateProduct3() throws Exception {
    Product product = new Product();
    product.setQuantity(1);
    product.setIsDeleted(true);
    product.setId(123L);
    product.setName("Name");
    product.setCategory("Category");
    product.setPrice(BigDecimal.valueOf(42L));
    product.setSerialNumber("42");
    product.setComment("Comment");

    Product product1 = new Product();
    product1.setQuantity(1);
    product1.setIsDeleted(true);
    product1.setId(123L);
    product1.setName("Name");
    product1.setCategory("Category");
    product1.setPrice(BigDecimal.valueOf(42L));
    product1.setSerialNumber("42");
    product1.setComment("Comment");
    when(this.productService.updateProduct((Product) any())).thenReturn(product1);
    when(this.productService.get((Long) any())).thenReturn(product);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/edit/partial/{id}", 123L);
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isFound())
        .andExpect(MockMvcResultMatchers.model().size(0))
        .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
  }

  @Test
  void testPartialUpdateProduct4() throws Exception {
    Product product = new Product();
    product.setQuantity(1);
    product.setIsDeleted(false);
    product.setId(123L);
    product.setName("Name");
    product.setCategory("Category");
    product.setPrice(BigDecimal.valueOf(42L));
    product.setSerialNumber("42");
    product.setComment("Comment");

    Product product1 = new Product();
    product1.setQuantity(1);
    product1.setIsDeleted(true);
    product1.setId(123L);
    product1.setName("Name");
    product1.setCategory("Category");
    product1.setPrice(BigDecimal.valueOf(42L));
    product1.setSerialNumber("42");
    product1.setComment("Comment");
    when(this.productService.updateProduct((Product) any())).thenReturn(product1);
    when(this.productService.get((Long) any())).thenReturn(product);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/edit/partial/{id}", 123L);
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isFound())
        .andExpect(MockMvcResultMatchers.model().size(0))
        .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
  }





  @Test
  void testAddProduct2() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/save", "Uri Vars");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  @Test
  void testAddProduct3() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/save")
        .param("id", "42");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  @Test
  void testAddProduct4() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/save")
        .param("serialNumber", "42");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  @Test
  void testCreateProductForm() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/new");
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(1))
        .andExpect(MockMvcResultMatchers.model().attributeExists("newProduct"))
        .andExpect(MockMvcResultMatchers.view().name("add_product"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("add_product"));
  }

  @Test
  void testCreateProductForm2() throws Exception {
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/product/new");
    getResult.contentType("Not all who wander are lost");
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(1))
        .andExpect(MockMvcResultMatchers.model().attributeExists("newProduct"))
        .andExpect(MockMvcResultMatchers.view().name("add_product"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("add_product"));
  }

  @Test
  void testDeleteProduct() throws Exception {
    when(this.productService.delete((Long) any())).thenReturn(true);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/delete/{id}", 123L);
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isFound())
        .andExpect(MockMvcResultMatchers.model().size(0))
        .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
  }

  @Test
  void testDeleteProduct2() throws Exception {
    when(this.productService.delete((Long) any())).thenReturn(true);
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/delete/{id}", 123L);
    getResult.contentType("Not all who wander are lost");
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isFound())
        .andExpect(MockMvcResultMatchers.model().size(0))
        .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
  }

  @Test
  void testEditProductForm() throws Exception {
    Product product = new Product();
    product.setQuantity(1);
    product.setIsDeleted(true);
    product.setId(123L);
    product.setName("Name");
    product.setCategory("Category");
    product.setPrice(BigDecimal.valueOf(42L));
    product.setSerialNumber("42");
    product.setComment("Comment");
    when(this.productService.get((Long) any())).thenReturn(product);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/product/edit/{id}", 123L);
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(1))
        .andExpect(MockMvcResultMatchers.model().attributeExists("editProduct"))
        .andExpect(MockMvcResultMatchers.view().name("edit_product"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("edit_product"));
  }

  @Test
  void testGetAllDeletedProducts() throws Exception {
    when(this.productService.findAllDeletedProducts()).thenReturn(null);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/deleted");
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(1))
        .andExpect(MockMvcResultMatchers.view().name("deleted_product"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("deleted_product"));
  }

  @Test
  void testGetAllDeletedProducts2() throws Exception {
    when(this.productService.findAllDeletedProducts()).thenReturn(null);
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/deleted");
    getResult.contentType("Not all who wander are lost");
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(1))
        .andExpect(MockMvcResultMatchers.view().name("deleted_product"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("deleted_product"));
  }

  @Test
  void testGetAllProducts() throws Exception {
    when(this.productService.findAllProducts(anyInt())).thenReturn(null);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(1))
        .andExpect(MockMvcResultMatchers.view().name("index"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("index"));
  }

  @Test
  void testGetAllProducts2() throws Exception {
    when(this.productService.findAllProducts(anyInt())).thenReturn(null);
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/");
    getResult.contentType("Not all who wander are lost");
    MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(1))
        .andExpect(MockMvcResultMatchers.view().name("index"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("index"));
  }

  @Test
  void testUpdateProduct() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/edit/{id}", 123L);
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.productController)
        .build()
        .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }


}

