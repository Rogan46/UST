package com.example.demo.repo;

import com.example.demo.Entity.Users;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MyRepo extends JpaRepository<Users, Integer> {
    Users findByusername(String username);

}
