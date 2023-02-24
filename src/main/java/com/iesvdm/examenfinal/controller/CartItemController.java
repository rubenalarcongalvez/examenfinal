package com.iesvdm.examenfinal.controller;

import com.iesvdm.examenfinal.domain.CartItem;
import com.iesvdm.examenfinal.service.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200") //No hace falta
@RequestMapping("/api/v1/cart")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    //Ruta sin rutas que contengan esos parámetros
    @GetMapping(value = {"", "/"})
    public List<CartItem> all() {
        return this.cartItemService.all();
    }

    //Ruta sin rutas que contengan esos parámetros
    @GetMapping("/user/{id_usuario}")
    public List<CartItem> allByUser(@PathVariable("id_usuario") Long id_usuario) {
        return this.cartItemService.allByUser(id_usuario);
    }

    @PostMapping({"", "/"})
    public CartItem newCartItem(@RequestBody CartItem cartItem) {
        cartItem.setCreatedDate(new Date());
        cartItem.setModifiedDate(new Date());
        return this.cartItemService.save(cartItem);
    }

    @GetMapping("/{id}")
    public CartItem one(@PathVariable("id") Long id) {
        return this.cartItemService.one(id);
    }

    @PutMapping("/{id}")
    public CartItem replaceCartItem(@PathVariable("id") Long id, @RequestBody CartItem cartItem) {
        return this.cartItemService.replace(id, cartItem);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable("id") Long id) {
        this.cartItemService.delete(id);
    }
    
}
