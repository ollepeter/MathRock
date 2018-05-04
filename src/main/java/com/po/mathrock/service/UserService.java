package com.po.mathrock.service;

import com.po.mathrock.model.Role;
import com.po.mathrock.model.User;

import java.util.List;

public interface UserService {

    User findByUsername (String username);
    String registerUser (User newUser);
    List<User> getAllUser();
    User addNewRole(User user, String newRoleName);

}
