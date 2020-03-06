package com.example.chatter.service;

import com.example.chatter.domain.Role;
import com.example.chatter.domain.User;
import com.example.chatter.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MailSender mailSender;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user)
    {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb != null) return false;

        user.setActivationCode(UUID.randomUUID().toString());
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        String message = String.format("Hello %s! \n" +
                "Please visit next link for activation your account: http://localhost:8080/activate/%s",user.getUsername(),user.getActivationCode());
        mailSender.send(user.getEmail(),"Activation code",message);
        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);

        userRepo.save(user);

        return true;
    }
}
