package com.design.renovation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SupplierData {
  @NotEmpty(message = "Name is required")
  private String name;

  @NotEmpty(message = "Address is required")
  private String address;

  @NotEmpty(message = "Email is required")
  @Email(message = "Email is not valid")
  private String email;

  private String phone;

}
