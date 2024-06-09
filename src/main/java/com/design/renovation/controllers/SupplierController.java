package com.design.renovation.controllers;

import com.design.renovation.dto.ResponseData;
import com.design.renovation.dto.SearchData;
import com.design.renovation.dto.SupplierData;
import com.design.renovation.models.entities.Category;
import com.design.renovation.models.entities.Supplier;
import com.design.renovation.services.SupplierService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {
  @Autowired
  private SupplierService supplierService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping
  public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierData supplierData, Errors errors) {
    ResponseData<Supplier> responseData = new ResponseData<>();
    List<String> errMessages = new ArrayList<>(List.of());
    if (errors.hasErrors()) {
      for(ObjectError error: errors.getAllErrors()) {
        errMessages.add(error.getDefaultMessage());
      }
      responseData.setMessages(errMessages);
      responseData.setStatus(false);
      responseData.setPayload(null);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }

//    Cara manual
//    Supplier supplier = new Supplier();
//    supplier.setName(supplierData.getName());
//    supplier.setAddress(supplierData.getAddress());
//    supplier.setEmail(supplierData.getEmail());

//    Cara cepat menggunakan mapper
    Supplier supplier = modelMapper.map(supplierData, Supplier.class);

    responseData.setStatus(true);
    responseData.setPayload(supplierService.save(supplier));
    return ResponseEntity.ok(responseData);
  }

  @GetMapping
  public Iterable<Supplier> findAll() {
    return supplierService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseData<Supplier>> findOne(@PathVariable("id") Long id) {
//    return supplierService.findOne(id);
    ResponseData<Supplier> responseData = new ResponseData<>();
    responseData.setPayload(supplierService.findOne(id));
    responseData.setStatus(true);
    return ResponseEntity.ok(responseData);
  }

  @PutMapping
  public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierData supplierData, Errors errors) {
    ResponseData<Supplier> responseData = new ResponseData<>();
    List<String> errMessages = new ArrayList<>(List.of());
    if (errors.hasErrors()) {
      for(ObjectError error: errors.getAllErrors()) {
        errMessages.add(error.getDefaultMessage());
      }
      responseData.setMessages(errMessages);
      responseData.setStatus(false);
      responseData.setPayload(null);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }

    Supplier supplier = modelMapper.map(supplierData, Supplier.class);

    responseData.setStatus(true);
    responseData.setPayload(supplierService.save(supplier));
    return ResponseEntity.ok(responseData);
  }

  @PostMapping("/search/email")
  public Supplier getSupplierByEmail(@RequestBody SearchData searchData) {
    return supplierService.getSupplierByEmail(searchData.getSearchKey());
  }

  @PostMapping("/search/address")
  public List<Supplier> getSupplierByAddress(@RequestBody SearchData searchData) {
    return supplierService.getSupplierContainAddress(searchData.getSearchKey());
  }

  @PostMapping("/search/nameoraddress")
  public List<Supplier> getSupplierByNameAddress(@RequestBody SearchData searchData) {
    return supplierService.getSupplierByNameOrAddress(searchData.getSearchKey(), searchData.getSearchSecondKey());
  }
}
