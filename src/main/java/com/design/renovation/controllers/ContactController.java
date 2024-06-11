package com.design.renovation.controllers;

import com.design.renovation.dto.ResponseData;
import com.design.renovation.helpers.CsvManipulation;
import com.design.renovation.models.entities.Contact;
import com.design.renovation.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {
  @Autowired
  private ContactService contactService;

  @GetMapping
  public ResponseEntity<ResponseData<Contact>> findAllContact() {
    ResponseData<Contact> responseData= new ResponseData<>();
    try {
      List<Contact> contacts = contactService.findAll();
      responseData.setStatus(true);
      responseData.setMessages(List.of("Get All Contacts"));
      responseData.setPayload((Contact) contacts);
      return ResponseEntity.ok(responseData);

    }catch (Exception err) {
      responseData.setStatus(false);
      responseData.setMessages(List.of("Could not get Contact " + err.getMessage()));
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
    }
  }

  @PostMapping("/upload")
  public ResponseEntity<ResponseData<Contact>> uploadFile(@RequestParam("file") MultipartFile file) {
    ResponseData<Contact> responseData = new ResponseData<>();
    if (!CsvManipulation.hasCSVFormat(file)) {
      responseData.setStatus(false);
      responseData.setMessages(List.of("Please upload a csv file"));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }

    try {
      List<Contact> contacts = contactService.save(file);
      responseData.setMessages(List.of("Csv file upload successfully : " + file.getOriginalFilename()));
      responseData.setStatus(true);
      responseData.setPayload(contacts.get(0)); //show record #1
      return ResponseEntity.ok(responseData);
    } catch (Exception err) {
      responseData.setStatus(false);
      responseData.setPayload(null);
      responseData.setMessages(List.of("Could not upload file '" + file.getOriginalFilename() + "'"));
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseData);
    }
  }
}
