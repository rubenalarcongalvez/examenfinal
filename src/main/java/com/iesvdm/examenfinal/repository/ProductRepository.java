package com.iesvdm.examenfinal.repository;

import com.iesvdm.examenfinal.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Page<Product> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

}