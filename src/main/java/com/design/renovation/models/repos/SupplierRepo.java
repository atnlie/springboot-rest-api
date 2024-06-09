package com.design.renovation.models.repos;

import com.design.renovation.models.entities.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {
  //example query drive by function name
  Supplier getByEmail(String email);

  List<Supplier> getByAddressContainingIgnoreCase(String address);

  List<Supplier> getByNameOrAddressContainingIgnoreCase(String name, String address);

}
