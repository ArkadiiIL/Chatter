package com.example.chatter.controller;

import com.example.chatter.domain.Role;
import com.example.chatter.domain.User;
import com.example.chatter.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model)
    {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb != null)
        {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        if(user.getUsername() == null || user.getUsername().equals(""))
        {
            model.addAttribute("message", "Please enter username");
            return "registration";
        }
        if(user.getPassword() == null || user.getPassword().equals(""))
        {
            model.addAttribute("message", "Please enter password");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}
