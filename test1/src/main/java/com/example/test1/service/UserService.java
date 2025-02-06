package com.example.test1.service;

import com.example.test1.model.Role;
import com.example.test1.model.User;
import com.example.test1.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return iUserRepository.findAll(pageable);
    }

    @Override
    public void save(User user) {
        try {
            if (user.getRole() == null) {
                user.setRole(Role.CUSTOMER);
            }
            if (user.getStatus() == null) {
                user.setStatus(true);
            }
            //user.setPassword(encryptPasswordUtils.encryptPasswordUtils(user.getPassword()));
            System.out.println("Before adding user");
            iUserRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Có lỗi khi thêm người dùng : " + e.getMessage());
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        return iUserRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return iUserRepository.existsByEmail(email);
    }

    @Override
    public User getById(Integer id) {
        return iUserRepository.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        iUserRepository.deleteById(id);
    }
}
