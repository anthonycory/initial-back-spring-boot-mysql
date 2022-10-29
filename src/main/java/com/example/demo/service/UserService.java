package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.repository.UserRepository;
import com.example.demo.entity.UserEntity;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Object> addUser(String email, String password) {
        UserEntity user = new UserEntity();
        user.setEmail("hh");
        user.setPassword("hh");
        userRepository.save(user);

        return new ResponseEntity<Object>(user, HttpStatus.ACCEPTED);
    }

    public List<UserEntity> findAll() {

        return userRepository.findAll();
    }

    // GET ONE USER
    /**
     * @param id
     * @return
     */
    public ResponseEntity<Object> getOneUser(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<Object>(user, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // DELETE USER
    /**
     * @param id
     * @return
     */
    public ResponseEntity<Object> deleteUser(Long id) {

        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return new ResponseEntity<Object>("succes", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<Object>("error", HttpStatus.BAD_REQUEST);
    }

    // Update user
    /**
     * @param id
     * @return
     */
    public ResponseEntity<Object> updateUser(Long id) {
        UserEntity user = userRepository.findById(id).get();
        user.setEmail("newemjjjjail@@email.fr");
        userRepository.save(user);
        return new ResponseEntity<Object>(user, HttpStatus.ACCEPTED);
    }
}
