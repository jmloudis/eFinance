package com.ist412.efinance.service;

import com.ist412.efinance.model.User;
import com.ist412.efinance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public List<User> getAllUsers() {

        return this.userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {

        // encode user password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //save user to repository
        this.userRepository.save(user);
//        this.userRepository.findById(user.getUid());

    }

    @Override
    public User getUserById(long uid) {
        Optional<User> optional = userRepository.findById(uid);
        User user = null;
        if (optional.isPresent()){
            user = optional.get();


        } else {
            throw new RuntimeException("User not found for id :: " + uid);
        }

        return user;
    }




}
