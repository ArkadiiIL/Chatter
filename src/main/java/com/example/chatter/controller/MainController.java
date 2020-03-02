package com.example.chatter.controller;

import com.example.chatter.domain.Message;
import com.example.chatter.domain.User;
import com.example.chatter.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;
    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model)
    {
        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages",messages);
        return "main";
    }

    @PostMapping("/main")
    public String post(@AuthenticationPrincipal User user, @RequestParam String text, @RequestParam(defaultValue = "noneTag") String tag, Model model)
    {
        if(text != null && !text.equals(""))
        {
            Message message = new Message(text,tag, user);
            messageRepo.save(message);
        }
        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages",messages);

        return "main";
    }
    @PostMapping("filter")
    public String filter(@RequestParam String filter, Model model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages", messages);

        return "main";
    }
}
