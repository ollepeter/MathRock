package com.po.mathrock.controller;

import com.po.mathrock.model.User;
import com.po.mathrock.service.UserService;
import com.po.mathrock.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class IndexController {

    private UserService userService;

    @Autowired
    // With the @Quailifier it can be decided which implementation of the Interface should be used.
    // The implementing class also should have a class level @Qualifier annotation to be recognized here.
    @Qualifier("UserServiceImpl")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("principalka", principal);
        return "index";
    }

    @RequestMapping(value = {"/registration"}, method = RequestMethod.GET)
    public String registration(Model model) {
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        return "registration";
    }

    @RequestMapping(value = {"/registration"}, method = RequestMethod.POST)
    public String registerUser(@ModelAttribute User newUser) {
        List<User> existingUsers = userService.getAllUser();
        for (User user : existingUsers) {
            if(user.getUsername().equals(newUser.getUsername())) {
                System.out.println("ALREADY EXISTS");
                return "authentication/login";
            }
        }
        userService.registerUser(newUser);
        return "authentication/login";
    }
}
