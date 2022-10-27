package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.repository.UserRepository;
import com.example.demo.entity.UserEntity;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> findAll() {

        return userRepository.findAll();
    }

    public UserEntity getOneUser(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        UserEntity userEntity = null;
        if (!user.isPresent()) {
            userEntity = user.get();
        }
        return userEntity;
    }

    public ResponseEntity<Object> deleteUser(Long id) {

        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return new ResponseEntity<Object>("succes", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<Object>("error", HttpStatus.BAD_REQUEST);
    }
}
