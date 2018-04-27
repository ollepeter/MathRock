package com.po.mathrock.service;

import com.po.mathrock.model.User;

import java.util.List;

public interface UserService {

    User findByUsername (String username);
    void registerUser (User newUser);
    List<User> getAllUser();

}
