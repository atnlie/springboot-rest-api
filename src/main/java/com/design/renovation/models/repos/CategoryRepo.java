package com.design.renovation.models.repos;

import com.design.renovation.models.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CategoryRepo extends PagingAndSortingRepository<Category, Long>, CrudRepository<Category, Long> {
  Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);
}

