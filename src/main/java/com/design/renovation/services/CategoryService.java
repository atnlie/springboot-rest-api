package com.design.renovation.services;

import com.design.renovation.models.entities.Category;
import com.design.renovation.models.repos.CategoryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CategoryService {
  @Autowired
  private CategoryRepo categoryRepo;

  public Category save(Category category) {
    if(category.getId() != null){
      Category curCategory = categoryRepo.findById(category.getId()).get();
      curCategory.setName(category.getName());
      category = curCategory;
    }
    return categoryRepo.save(category);
  }

  public Category findOne(Long id) {
    Optional<Category> category = categoryRepo.findById(id);
    return category.orElse(null);
  }

  public Iterable<Category> findAll() {
    return categoryRepo.findAll();
  }

  public void removeOne(Long id) {
    categoryRepo.deleteById(id);
  }

  public Iterable<Category> getCategoryByName(String name, Pageable pageable) {
    return categoryRepo.findByNameContainingIgnoreCase(name, pageable);
  }

  public Iterable<Category> saveBatch(Iterable<Category> categories) {
    return categoryRepo.saveAll(categories);
  }

}
