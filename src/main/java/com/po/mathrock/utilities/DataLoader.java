package com.po.mathrock.utilities;


import com.po.mathrock.model.Role;
import com.po.mathrock.model.User;
import com.po.mathrock.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private UserService userService;

    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //create and save userUser
        User user = new User("user", "user");
        userService.saveNewUser(new User("user", "user"));
        //add roles to userUser
        userService.addNewRole(user, new Role("USER"));


        //create and save adminUser
        User admin = new User("admin", "admin");
        userService.saveNewUser(admin);
        //add roles to adminUser
        userService.addNewRole(admin, new Role("USER"));
        userService.addNewRole(admin, new Role("USER"));
        userService.addNewRole(admin, new Role("TEACHER"));
        userService.addNewRole(admin, new Role("ADMIN"));

    }
}