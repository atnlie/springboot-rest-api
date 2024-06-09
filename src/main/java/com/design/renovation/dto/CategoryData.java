package com.design.renovation.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryData {
  @NotEmpty(message = "Name is required")
  private String name;

  private Long id;

}
