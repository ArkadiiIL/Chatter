package com.example.chatter;

import com.example.chatter.domain.Message;
import com.example.chatter.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
    @Autowired
    private MessageRepo messageRepo;
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Model model)
    {
        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages",messages);
        return "main";
    }

    @PostMapping
    public String post(@RequestParam String text, @RequestParam(defaultValue = "noneTag") String tag, Model model)
    {
        if(text != null && !text.equals(""))
        {
            Message message = new Message(text,tag);
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
