package com.design.renovation.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Entity
@Table(name = "tbl_product")
public class Product implements Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @NotEmpty(message = "Name is required")
  @Column(name = "product_name", length = 100)
  private String name;

  @NotEmpty(message = "Description is required")
  @Column(name = "product_description", length = 500)
  private String description;

  private double price;

  @ManyToOne
  private Category category;

  @ManyToMany
//  @JoinTable(
//      name = "tbl_product_supplier_atn",
//      joinColumns = @JoinColumn(name = "product_id_atn"),
//      inverseJoinColumns = @JoinColumn(name = "supplier_id_atn")
//  )
  private Set<Supplier> suppliers;

}
