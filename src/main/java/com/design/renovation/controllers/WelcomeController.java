package com.design.renovation.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class WelcomeController {

  @GetMapping
  public String welcome() {
    return "Selamat datang di Spring Boot Rest API V1";
  }
}
