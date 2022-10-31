package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.Bcrypt;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @param email
     * @param password
     * @return
     */
    public ResponseEntity<Object> login(String email, String password) {
        boolean emailExist = userRepository.findByEmail(email) != null;
        if (emailExist) {
            boolean passwordMatch = Bcrypt.check(password, userRepository.findByEmail(email).getPassword());
            if (passwordMatch) {
                return new ResponseEntity<>(userRepository.findByEmail(email), HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<Object>("Mot de passe incorrect", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Object>("Email ou mot de passe incorrect", HttpStatus.BAD_REQUEST);

    }
}
