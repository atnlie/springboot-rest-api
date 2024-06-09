package com.design.renovation.controllers;

import com.design.renovation.dto.ResponseData;
import com.design.renovation.dto.UserData;
import com.design.renovation.models.entities.AppUser;
import com.design.renovation.services.UserService;
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
@RequestMapping("/api/v1/users")
public class AppUserController {
  @Autowired
  private UserService appUserService;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping
  public Iterable<AppUser> findAll() {
    return appUserService.getAllUsers();
  }


  @PostMapping("/register")
  public ResponseEntity<ResponseData<AppUser>> register(@Valid @RequestBody UserData userData, Errors errors) {
    ResponseData<AppUser> responseData = new ResponseData<>();
    List<String> errMessages = new ArrayList<>(List.of());

    if(errors.hasErrors()) {
      for (ObjectError error : errors.getAllErrors()) {
        errMessages.add(error.getDefaultMessage());
      }
      responseData.setMessages(errMessages);
      responseData.setStatus(false);
      responseData.setPayload(null);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }

    AppUser appUser = modelMapper.map(userData, AppUser.class);
    responseData.setStatus(true);
    responseData.setPayload(appUserService.registerAppUser(appUser));
    responseData.setMessages(List.of("User saved."));
    return ResponseEntity.ok(responseData);
  }
}
