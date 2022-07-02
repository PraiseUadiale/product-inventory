package com.praise.io.productinventory.repository;

import com.praise.io.productinventory.model.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query(value = "SELECT * from Product p where p.is_deleted=false ", nativeQuery = true)
  Page<Product> findAll(Pageable pageable);

  @Query(value = "SELECT * from Product p where p.is_deleted=true ", nativeQuery = true)
  List<Product> findAllRecentlyDeletedProducts();
}
