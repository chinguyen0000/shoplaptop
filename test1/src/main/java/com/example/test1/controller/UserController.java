package com.example.test1.controller;

import com.example.test1.model.Role;
import com.example.test1.model.User;
import com.example.test1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("/list")
    public String listAllUser(Model model) {
        model.addAttribute("users", iUserService.findAll());
        return "user/list";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", Role.values());
        return "user/create";
    }
}
