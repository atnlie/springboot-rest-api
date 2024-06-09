package com.design.renovation.controllers;

import com.design.renovation.dto.CategoryData;
import com.design.renovation.dto.ResponseData;
import com.design.renovation.dto.SearchData;
import com.design.renovation.models.entities.Category;
import com.design.renovation.services.CategoryService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryContoller {
  @Autowired
  private CategoryService categoryService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping
  public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryData categoryData, Errors errors) {
    ResponseData<Category> responseData = new ResponseData<>();
    List<String> errMessages = new ArrayList<>(List.of());
    if(errors.hasErrors()) {
      for(ObjectError error : errors.getAllErrors()) {
        errMessages.add(error.getDefaultMessage());
      }
      responseData.setMessages(errMessages);
      responseData.setStatus(false);
      responseData.setPayload(null);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }
    Category category = modelMapper.map(categoryData, Category.class);
    responseData.setStatus(true);
    responseData.setPayload(categoryService.save(category));
    return ResponseEntity.ok(responseData);
  }

  @GetMapping
  public Iterable<Category> findAll() {
    return categoryService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseData<Category>> findOne(@PathVariable("id") Long id) {
    ResponseData<Category> responseData = new ResponseData<>();
    responseData.setPayload(categoryService.findOne(id));
    responseData.setStatus(true);
    return ResponseEntity.ok(responseData);
  }

  @PutMapping
  public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody CategoryData categoryData, Errors errors) {
    ResponseData<Category> responseData = new ResponseData<>();
    List<String> errMessages = new ArrayList<>(List.of());
    if(errors.hasErrors()) {
      for(ObjectError error : errors.getAllErrors()) {
        errMessages.add(error.getDefaultMessage());
      }
      responseData.setMessages(errMessages);
      responseData.setStatus(false);
      responseData.setPayload(null);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }
    Category category = modelMapper.map(categoryData, Category.class);
    responseData.setStatus(true);
    responseData.setPayload(categoryService.save(category));
    return ResponseEntity.ok(responseData);
  }

  @PostMapping("/search/{size}/{page}")
  public Iterable<Category> getCategoryByName(@RequestBody SearchData searchData,
                                              @PathVariable("size") int size,
                                              @PathVariable("page") int page) {
    Pageable pageable = PageRequest.of(page, size);
    return categoryService.getCategoryByName(searchData.getSearchKey(), pageable);
  }

  @PostMapping("/search/{size}/{page}/{sort}")
  public Iterable<Category> getCategoryByName(@RequestBody SearchData searchData,
                                              @PathVariable("size") int size,
                                              @PathVariable("page") int page,
                                              @PathVariable("sort") String sort) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
    if (sort.equalsIgnoreCase("desc")) {
      pageable = PageRequest.of(page, size, Sort.by("id").descending());
    } else {
      pageable = PageRequest.of(page, size, Sort.by("id").ascending());
    }
    return categoryService.getCategoryByName(searchData.getSearchKey(), pageable);
  }

  @PostMapping("/batch")
  public ResponseEntity<ResponseData<Iterable<Category>>> saveBatch(@Valid @RequestBody Category[] categories) {
    ResponseData<Iterable<Category>> responseData = new ResponseData<>();
    responseData.setPayload(categoryService.saveBatch(Arrays.asList(categories)));  // convert array to iterable
    responseData.setStatus(true);

    return ResponseEntity.ok(responseData);
  }

}
