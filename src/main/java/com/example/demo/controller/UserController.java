package com.example.demo.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.service.UserService;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @PostMapping(path = "/add")
    public ResponseEntity<Object> addUser(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
        return userService.addUser(email, password);
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

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return userService.updateUser(id ,body);
    }

}