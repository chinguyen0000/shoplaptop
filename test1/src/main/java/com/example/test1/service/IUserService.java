package com.example.test1.service;

import com.example.test1.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    Page<User> findAll(Pageable pageable);
    void save(User user);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
