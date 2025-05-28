package com.example.demoelg.service;

import com.example.demoelg.model.User;
import com.example.demoelg.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private UserRespository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        return false;
    }

    public User updateById(User request, Long id) {
        return null;
    }

    public Optional<User> getById(Long id) {
        return null;
    }

    public ArrayList<User> getUsers() {
        return null;
    }
}