package com.example.test1.controller;

import com.example.test1.model.Role;
import com.example.test1.model.User;
import com.example.test1.model.dto.UserDTO;
import com.example.test1.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/dashboard/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("/list")
    public String listAllUser(@RequestParam(name = "page", defaultValue = "0", required = false) int page, Model model) {
        Pageable pageable = PageRequest.of(page, 3);
        model.addAttribute("users", iUserService.findAll(pageable));
        return "user/list";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles", Role.values());
        return "user/create";
    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult, Model model) {
        System.out.println("++++++++++addUser+++++++++");
        if (iUserService.existsByUsername(userDTO.getUsername())) {
            bindingResult.rejectValue("username", "", "Username is already taken");
        }
        if (iUserService.existsByEmail(userDTO.getEmail())) {
            bindingResult.rejectValue("email", "", "Email is already taken");
        }
        new UserDTO().validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", Role.values());
            model.addAttribute("user", userDTO);
            return "user/create";
        }
        System.out.println("Before creating new User, userDTO is: " + userDTO);
        User newUser = new User();
        BeanUtils.copyProperties(userDTO, newUser);
        System.out.println("Mapping new user");
        iUserService.save(newUser);
        return "redirect:/dashboard/user/list";
    }

    @GetMapping("/{id}/detail")
    public String showDetail(@PathVariable Integer id, Model model) {
        model.addAttribute("user",iUserService.getById(id));
        return "user/detail";
    }

    @GetMapping("/{id}/edit")
    public String showEditPage(@PathVariable Integer id, Model model) {
        UserDTO userDTO = new UserDTO();
        User user = iUserService.getById(id);
        BeanUtils.copyProperties(user, userDTO);
        model.addAttribute("userDTO",userDTO);
        model.addAttribute("roles", Role.values());
        return "user/edit";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult, Model model) {
        User user = iUserService.getById(userDTO.getId());

        if (!user.getEmail().equals(userDTO.getEmail()) && iUserService.existsByEmail(userDTO.getEmail())) {
            bindingResult.rejectValue("email", "", "Email is already taken");
        }

        new UserDTO().validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", Role.values());
            model.addAttribute("userDTO", userDTO);
            return "user/edit";
        }

        BeanUtils.copyProperties(userDTO, user);
        iUserService.save(user);
        return "redirect:/dashboard/user/list";
    }

    @GetMapping("/{id}/remove")
    public String removeUser(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            iUserService.deleteById(id);
            redirectAttributes.addFlashAttribute("messageType", "success");
            redirectAttributes.addFlashAttribute("message", "Xoá thành công!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("messageType", "error");
            redirectAttributes.addFlashAttribute("message", "Có lỗi khi xoá người dùng này!");
        }
        return "redirect:/dashboard/user/list";
    }
}
