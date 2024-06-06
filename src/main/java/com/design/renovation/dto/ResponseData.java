package com.design.renovation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseData<T> {
  private boolean status;
  private List<String> messages;
  private T payload;

}
