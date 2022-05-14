package com.praise.io.shopifychallenge2022.repository;

import com.praise.io.shopifychallenge2022.model.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query(value = "SELECT * from Product p where p.deleted=false ", nativeQuery = true)
  Page<Product> findAll(Pageable pageable);

  @Modifying
  @Query(value = "Update Product p SET p.deleted=?  where p.id= ? ", nativeQuery = true)
  void softDelete(Boolean isDeleted, Long id);

  List<Product> findAllByDeletedIsTrue();
}
