package com.design.renovation.models.repos;

import com.design.renovation.models.entities.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<AppUser, Long> {
  Optional<AppUser> findByEmail(String email);
}
