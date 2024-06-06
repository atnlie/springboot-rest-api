package com.design.renovation.controllers;

import com.design.renovation.dto.ResponseData;
import com.design.renovation.models.entities.Product;
import com.design.renovation.models.repos.ProductRepo;
import com.design.renovation.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
  @Autowired
  private ProductService productService;
  @Autowired
  private ProductRepo productRepo;

  @PostMapping
  public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors) {
    ResponseData<Product> responseData = new ResponseData<>();
    List<String> errData = new ArrayList<>(List.of());
    if(errors.hasErrors()) {
      for (ObjectError error: errors.getAllErrors()) {
        errData.add(error.getDefaultMessage());
      }

      responseData.setMessages(errData);
      responseData.setStatus(false);
      responseData.setPayload(null);
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(responseData);
    }

    responseData.setStatus(true);
    responseData.setPayload(productService.save(product));
    return ResponseEntity.ok(responseData);
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
  public ResponseEntity<ResponseData<Product>> update(@RequestBody Product product, Errors errors) {
    ResponseData<Product> responseData = new ResponseData<>();
    List<String> errData = new ArrayList<>(List.of());
    if(errors.hasErrors()) {
      for (ObjectError error: errors.getAllErrors()) {
        errData.add(error.getDefaultMessage());
        //responseData.getMessages().add(error.getDefaultMessage()); // gabise
        //responseData.setMessages(Collections.singletonList(error.getDefaultMessage())); bisa tapi single message
      }

      responseData.setMessages(errData);
      responseData.setStatus(false);
      responseData.setPayload(null);
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(responseData);
    }

    responseData.setStatus(true);
    responseData.setPayload(productService.save(product));
    return ResponseEntity.ok(responseData);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") Long id) {
    productService.removeOne(id);
  }

}
