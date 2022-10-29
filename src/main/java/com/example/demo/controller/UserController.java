package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody String addNewUser(@RequestParam String email, @RequestParam String password) {
        return "hello";
    }

    @GetMapping(path = "/all")
    public ResponseEntity getAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable Long id) {
        return userService.getOneUser(id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping(path = "/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id) {
        return userService.updateUser(id);
    }

}