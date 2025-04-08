package com.example.demo.service;

import com.example.demo.Entity.UserPrincipal;
import com.example.demo.Entity.Users;
import com.example.demo.repo.MyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyService implements UserDetailsService {
    @Autowired
    private MyRepo myRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user= myRepo.findByusername(username);
        if (user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("not found");
        }
        return new UserPrincipal(user);
    }
}