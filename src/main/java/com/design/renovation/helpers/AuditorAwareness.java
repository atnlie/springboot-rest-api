package com.design.renovation.helpers;

import com.design.renovation.models.entities.AppUser;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareness implements AuditorAware<String> {
  @Override
  public Optional<String> getCurrentAuditor() {
    AppUser currentUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    System.out.println("Hasil " + currentUser);
    System.out.println("Hasil Email " + currentUser.getEmail());
    return Optional.of(currentUser.getEmail());
  }
}
