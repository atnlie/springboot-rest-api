package com.design.renovation.services;

import com.design.renovation.models.entities.Supplier;
import com.design.renovation.models.repos.SupplierRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SupplierService {
  @Autowired
  private SupplierRepo supplierRepo;

  public Supplier save(Supplier supplier) {
    return supplierRepo.save(supplier);
  }

  public Supplier findOne(Long id) {
    Optional<Supplier> supplier = supplierRepo.findById(id);
    if (supplier.isEmpty()) {
      return null;
    }
    return supplier.get();
  }

  public Iterable<Supplier> findAll() {
    return supplierRepo.findAll();
  }

  public void removeOne(Long id) {
    supplierRepo.deleteById(id);
  }

  public Supplier getSupplierByEmail(String email) {
    return supplierRepo.getByEmail(email);
  }

  public List<Supplier> getSupplierContainAddress(String address) {
    return supplierRepo.getByAddressContainingIgnoreCase(address);
  }

  public List<Supplier> getSupplierByNameOrAddress(String name, String address) {
    return supplierRepo.getByNameOrAddressContainingIgnoreCase(name, address);
  }

}
