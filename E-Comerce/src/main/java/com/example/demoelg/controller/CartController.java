package com.example.demoelg.controller;

import com.example.demoelg.model.Cart;
import com.example.demoelg.model.User;
import com.example.demoelg.service.CartService;
import com.example.demoelg.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ArrayList<User> getCart(){
        return this.cartService.getCarts();
    }

    @PostMapping
    public User saveCart(User users){
        Cart cart = null;
        return this.cartService.saveCart(cart);
    }

    @PostMapping
    public User saveCart(@RequestBody User users){
        return this.cartService.saveCart(cart);
    }

    @GetMapping(path = "/{id}")
    public Optional<User> getUsersById(@PathVariable Long id){
        return this.cartService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public User updateUserById(@RequestBody User request, Long id){
        return this.cartService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.cartService.deleteCart(id);

        if(ok){
            return "User with id " + id + "deleted";
        } else {
            return "User with id " + id + "not found";
        }
    }

}
