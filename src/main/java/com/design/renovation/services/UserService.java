package com.design.renovation.services;

import com.design.renovation.helpers.PassEncoder;
import com.design.renovation.models.entities.AppUser;
import com.design.renovation.models.repos.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService implements UserDetailsService {
  @Autowired
  private UserRepo appUserRepo;

  @Autowired
  private PassEncoder passEncoder;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return appUserRepo.findByEmail(email).orElseThrow(
        () -> new UsernameNotFoundException(String.format("User with email '%s' not found.", email)));
  }

  public AppUser registerAppUser(AppUser appUser) {
    boolean userExists = appUserRepo.findByEmail(appUser.getEmail()).isPresent();
    if(userExists){
      throw new RuntimeException(
          String.format("User with email '%s' already exist.", appUser.getEmail())
      );
    }

    // don't forget to encode password
    String encodedPass = passEncoder.bCryptPasswordEncoder().encode(appUser.getPassword());
    appUser.setPassword(encodedPass);
    return appUserRepo.save(appUser);
  }

  public Iterable<AppUser> getAllUsers() {
    return appUserRepo.findAll();
  }

}
