package com.design.renovation.models.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Entity
@Table(name = "tbl_product")
public class Product implements Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @Column(name = "product_name", length = 100)
  private String name;

  @Column(name = "product_description", length = 500)
  private String description;

  private double price;

}
