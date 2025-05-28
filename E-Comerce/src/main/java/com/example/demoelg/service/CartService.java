package com.example.demoelg.service;

import com.example.demoelg.model.Cart;
import com.example.demoelg.model.User;
import com.example.demoelg.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService  {

    @Autowired
private CartRepository cartRepository;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Optional<Cart> getCartById(Long id) {
        return cartRepository.findById(id);
    }

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    public ArrayList<User> getCarts() {
    }

    public Optional<User> getById(Long id) {
    }

    public User updateById(User request, Long id) {
        return null;
    }
}