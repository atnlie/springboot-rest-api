package com.design.renovation.models.repos;

import com.design.renovation.models.entities.Product;
import com.design.renovation.models.entities.Supplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Long> {
  List<Product> findByNameContains(String name);

  @Query("SELECT p FROM Product p WHERE lower(p.name) = lower(:name)")
  Product findProductByName(@PathVariable("name") String name);

  @Query("SELECT p FROM Product p WHERE lower(p.name) like CONCAT('%', lower(:name), '%')")
  List<Product> findProductByNameLike(@PathVariable("name") String name);

  @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
  List<Product> getProductByCategory(@PathVariable("categoryId") Long categoryId);

  @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
  List<Product> getProductBySupplier(Supplier supplier);
}
