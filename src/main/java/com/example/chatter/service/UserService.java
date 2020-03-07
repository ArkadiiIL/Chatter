package com.example.chatter.service;

import com.example.chatter.domain.Role;
import com.example.chatter.domain.User;
import com.example.chatter.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

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
        sendMessage(user);
        return true;
    }

    private void sendMessage(User user) {
        String message = String.format("Hello %s! \n" +
                "Please visit next link for activation your account: http://localhost:8080/activate/%s",user.getUsername(),user.getActivationCode());
        mailSender.send(user.getEmail(),"Activation code",message);
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

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(String username, Map<String, String> form, User user) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.getRoles().clear();
        for(String role : form.keySet())
        {
            if(roles.contains(role))
            {
                user.getRoles().add(Role.valueOf(role));
            }
        }
        userRepo.save(user);
    }

    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();
        boolean isChanged = (email != null && !email.equals(userEmail) || userEmail != null && !userEmail.equals(email));
        if(isChanged)
        {
            if(!StringUtils.isEmpty(email))
            {
                user.setEmail(email);
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }
        if(!StringUtils.isEmpty(password))
        {
            user.setPassword(password);
        }
        userRepo.save(user);
        if(isChanged) sendMessage(user);
    }
}
