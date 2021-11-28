package com.ist412.efinance.controller;


import com.ist412.efinance.model.User;
import com.ist412.efinance.repository.UserRepository;
import com.ist412.efinance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    public String home(){
        return "/";
    }


    @GetMapping("/showNewUserForm")
    public String showNewUserForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/";
    }


    @GetMapping("/showFormForUpdate/{uid}")
    public String showFormForUpdate(@PathVariable("uid") long uid, Model model){
        User user = userService.getUserById(uid);
        model.addAttribute("user", user);
        //model.getAttribute(user.getUid().toString());
        return "update_user";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userService.getAllUsers();
        model.addAttribute("listUsers", listUsers);
        return "index";
    }

}
