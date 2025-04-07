package com.example.demo.repo;

import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

public interface MyRepo extends JpaRepository<User, Integer> {
}
