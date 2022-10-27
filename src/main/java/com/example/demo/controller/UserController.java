package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    // @PostMapping(path = "/add")
    // public @ResponseBody String addNewUser(@RequestParam String email,
    // @RequestParam String password) {

    // UserEntity user = new UserEntity();
    // user.setEmail(email);
    // user.setPassword(password);
    // userRepository.save(user);
    // return "User Created";
    // }

    // @GetMapping(path = "/all")
    // public @ResponseBody List<UserEntity> getAllUsers() {
    // return userRepository.findAll();
    // }

    @GetMapping(path = "/all")
    public List<UserEntity> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody UserEntity getOneUser(@PathVariable Long id) {
        return userService.getOneUser(id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    // @GetMapping(path = "/update/{id}")
    // public @ResponseBody Optional<UserEntity> updateUser(@PathVariable Long id) {

    // UserEntity user = userRepository.findById(id).get();
    // user.setEmail("newemjjjjail@@email.fr");
    // userRepository.save(user);
    // return userRepository.findById(id);
    // }

}