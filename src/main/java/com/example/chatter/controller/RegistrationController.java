package com.example.chatter.controller;

import com.example.chatter.domain.User;
import com.example.chatter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
    @GetMapping("/registration")
    public String registration(Model model)
    {
        model.addAttribute("message","");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model)
    {
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
        if(user.getEmail() == null || user.getEmail().equals(""))
        {
            model.addAttribute("message", "Please enter email");
            return "registration";
        }
        if(!userService.addUser(user))
        {
            model.addAttribute("message","User exists");
            return "registration";
        }

        return "login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("message", "Activation code is not found!");
        }

        return "login";
    }
}
