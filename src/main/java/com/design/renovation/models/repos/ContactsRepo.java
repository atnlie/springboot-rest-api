package com.design.renovation.models.repos;

import com.design.renovation.models.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepo extends JpaRepository<Contact, Long> {

}
