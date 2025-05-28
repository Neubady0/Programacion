package com.example.demoelg.controller;

import com.example.demoelg.model.User;
import com.example.demoelg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping
    public ArrayList<User> getUsers(){
        return this.userService.getUsers();
    }

    @PostMapping
    public User saveUser(User users){
        return this.userService.saveUser(users);
    }

    @PostMapping
    public User saveuser(@RequestBody User users){
        return this.userService.saveUser(users);
    }

    @GetMapping(path = "/{id}")
    public Optional<User> getUsersById(@PathVariable Long id){
        return this.userService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public User updateUserById(@RequestBody User request, Long id){
        return this.userService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.userService.deleteUser(id);

        if(ok){
            return "User with id " + id + "deleted";
        } else {
            return "User with id " + id + "not found";
        }
    }

}
