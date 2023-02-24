package com.iesvdm.examenfinal.repository;

import com.iesvdm.examenfinal.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    public List<CartItem> findAllByUserId(long id);

}
