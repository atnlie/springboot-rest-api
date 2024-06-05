package com.design.renovation.controllers;

import com.design.renovation.models.entities.Product;
import com.design.renovation.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
  @Autowired
  private ProductService productService;

  @PostMapping
  public Product create(@RequestBody Product product) {
    return productService.save(product);
  }

  @GetMapping
  public Iterable<Product> findAll() {
    return productService.findAll();
  }

  @GetMapping("/{id}")
  public Product getProductById(@PathVariable("id") Long id) {
    return productService.findOne(id);
  }

  @PutMapping
  public Product update(@RequestBody Product product) {
    return productService.save(product);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") Long id) {
    productService.removeOne(id);
  }

}
