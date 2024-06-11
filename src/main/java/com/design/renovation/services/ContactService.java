package com.design.renovation.services;

import com.design.renovation.helpers.CsvManipulation;
import com.design.renovation.models.entities.Contact;
import com.design.renovation.models.repos.ContactsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service("contactService")
@Transactional
public class ContactService {
  @Autowired
  private ContactsRepo contactsRepo;

  public List<Contact> save(MultipartFile file) {
    try {
      List<Contact> contacts = CsvManipulation.csvToContacts(file.getInputStream());
      return contactsRepo.saveAll(contacts);

    } catch (IOException err) {
      throw new RuntimeException("Fail to store csv data : " + err.getMessage());
    }
  }

  public List<Contact> findAll() {
    return contactsRepo.findAll();
  }
}
