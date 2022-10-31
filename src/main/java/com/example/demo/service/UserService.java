package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.repository.UserRepository;
import com.example.demo.utils.Bcrypt;
import com.example.demo.entity.UserEntity;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create user
    /**
     * @param email
     * @param password
     * @return
     */
    public ResponseEntity<Object> addUser(String email, String password) {
        boolean emailExist = userRepository.findByEmail(email) == null;
        if(emailExist) {
            if (password != null && !password.isEmpty() && password.length() >= 8) {
                UserEntity user = new UserEntity();
                user.setEmail(email);
                user.setPassword(Bcrypt.hash(password));
                userRepository.save(user);
                return new ResponseEntity<Object>(user, HttpStatus.CREATED);
            }
            return new ResponseEntity<Object>("Mot de passe incorrect", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Object>("Email déjà utilisé", HttpStatus.BAD_REQUEST);
    }

    // Get all users
    /**
     * @return
     */
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    // Get one user
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

    // Delete user
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
     * @param body
     * @return
     */
    public ResponseEntity<Object> updateUser(Long id, Map<String, String> body) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserEntity userEntity = user.get();
            userEntity.setEmail(body.get("email"));
            userEntity.setPassword(Bcrypt.hash(body.get("password")));
            userRepository.save(userEntity);
            return new ResponseEntity<Object>(userEntity, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
