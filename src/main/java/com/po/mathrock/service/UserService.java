package com.po.mathrock.service;

import com.po.mathrock.model.Role;
import com.po.mathrock.model.User;

import java.util.List;

public interface UserService {

    User findByUsername (String username);
    void registerUser (User newUser);
    List<User> getAllUser();
    void addNewRole(User user, Role role);
    void saveNewUser(User newUser);

}
