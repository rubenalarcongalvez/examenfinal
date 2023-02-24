package com.iesvdm.examenfinal.service;

import com.iesvdm.examenfinal.domain.CartItem;
import com.iesvdm.examenfinal.repository.CartItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> all() {
        return this.cartItemRepository.findAll();
    }

    public List<CartItem> allByUser(long id) {
        return this.cartItemRepository.findAllByUserId(id);
    }

    public CartItem save(CartItem cartItem) {
        return this.cartItemRepository.save(cartItem);
    }

    public CartItem one(Long id) {
        return this.cartItemRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public CartItem replace(Long id, CartItem cartItem) {
        return this.cartItemRepository.findById(id).map( p -> (id.equals(cartItem.getId())  ?
                        this.cartItemRepository.save(cartItem) : null))
                .orElseThrow(RuntimeException::new);

    }

    public void delete(Long id) {
        this.cartItemRepository.findById(id).map(p -> {this.cartItemRepository.delete(p);
                    return p;})
                .orElseThrow(RuntimeException::new);
    }
    
}
