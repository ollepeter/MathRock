package com.po.mathrock.service;

import com.po.mathrock.model.Role;
import com.po.mathrock.model.User;
import com.po.mathrock.repositories.RoleRepository;
import com.po.mathrock.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DBLoader {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserService userService;

    @Autowired
    public DBLoader(UserRepository userRepository, RoleRepository roleRepository, UserService userService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
        loadUsers();
    }

    private void loadUsers() {
        System.out.println("Loding the Users!");

        User user1 = new User("sajt", "sajtpass");
        userService.addNewRole(user1, "USER");
//        userService.addNewRole(user1, "ADMIN");

        userRepository.save(user1);
        System.out.println("user1 loaded!");


        User user2 = new User("kifli", "kiflipass");
        userService.addNewRole(user2, "USER");
//
        userRepository.save(user2);
        System.out.println("user2 loaded!");


//        User user1 = new User("sajt", "sajtpass");
//        User user2 = new User("kifli", "kiflipass");
//
//        Role role1 = new Role("USER");
//        Role role2 = new Role("ADMIN");
//
//        user1.getUserRoles().add(role1);
//        user1.getUserRoles().add(role2);
//
//
//        user2.getUserRoles().add(role2);
//
//
//        userRepository.save(user1);
//        userRepository.save(user2);



    }


}

