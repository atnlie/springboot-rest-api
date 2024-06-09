package com.design.renovation.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserData {
  private String name;
  private String email;
  private String password;
  private String userRole;
}
