package com.design.renovation.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name="tbl_supplier")
public class Supplier implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 200, nullable = false)
  private String name;

  @Column(length = 300, nullable = false)
  private String address;

  @Column(length = 100, nullable = false, unique = true)
  private String email;

  @Column(length = 30)
  private String phone;

  @ManyToMany
  @JoinTable(
      name = "tbl_supplier_product_atn",
      joinColumns = @JoinColumn(name = "supplier_id_atn"),
      inverseJoinColumns = @JoinColumn(name = "product_id_atn")
  )
  private Set<Product> products;
}

