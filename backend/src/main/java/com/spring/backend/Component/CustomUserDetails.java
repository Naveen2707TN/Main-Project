package com.spring.backend.Component;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.spring.backend.Entity.userEntity;
import com.spring.backend.Exception.UserException;
import com.spring.backend.Repo.userRepo;

@Component
public class CustomUserDetails implements UserDetailsService{

    @Autowired private userRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userEntity entity = userRepo.findByName(username);
        if (entity == null) {
            throw new UserException("user name not found");
        }
        return new User(entity.getEmail(), entity.getPass(), new ArrayList<>());
    }
    
}
