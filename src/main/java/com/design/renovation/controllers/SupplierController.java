package com.design.renovation.controllers;

import com.design.renovation.dto.ResponseData;
import com.design.renovation.dto.SupplierData;
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
    if (errors.hasErrors()) {
      for(ObjectError error: errors.getAllErrors()) {
        responseData.getMessages().add(error.getDefaultMessage());
      }
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
  public Supplier findOne(@PathVariable("id") Long id) {
    return supplierService.findOne(id);
  }

  @PutMapping
  public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierData supplierData, Errors errors) {
    ResponseData<Supplier> responseData = new ResponseData<>();
    if (errors.hasErrors()) {
      for(ObjectError error: errors.getAllErrors()) {
        responseData.getMessages().add(error.getDefaultMessage());
      }
      responseData.setStatus(false);
      responseData.setPayload(null);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }

    Supplier supplier = modelMapper.map(supplierData, Supplier.class);

    responseData.setStatus(true);
    responseData.setPayload(supplierService.save(supplier));
    return ResponseEntity.ok(responseData);
  }
}
