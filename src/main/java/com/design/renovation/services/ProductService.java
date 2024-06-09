package com.design.renovation.services;

import com.design.renovation.models.entities.Product;
import com.design.renovation.models.entities.Supplier;
import com.design.renovation.models.repos.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
  @Autowired
  private ProductRepo productRepo;

  public Product save(Product product) {
    return productRepo.save(product);
  }

  public Product findOne(Long id) {
    Optional<Product> prd = productRepo.findById(id);
    if (prd.isEmpty()) {
      return null;
    }
      return productRepo.findById(id).get();

  }

  public Iterable<Product> findAll() {
    return productRepo.findAll();
  }

  public void removeOne(Long id) {
    productRepo.deleteById(id);
  }

  public List<Product> findByName(String name) {
    return productRepo.findByNameContains(name);
  }

  public void addSupplier(Supplier supplier, Long productId) {
    Product product = findOne(productId);
    if (product == null) {
      throw new RuntimeException("Product with ID: " + productId + " not found.");
    }
    product.getSuppliers().add(supplier);
    save(product);
  }

  public Product getProductByName(String name) {
    return productRepo.findProductByName(name);
  }

  public List<Product> getProductByNameLike(String name) {
    return productRepo.findProductByNameLike(name);
  }

}
