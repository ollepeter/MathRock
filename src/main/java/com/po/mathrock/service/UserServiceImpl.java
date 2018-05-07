package com.po.mathrock.service;

import com.po.mathrock.model.Role;
import com.po.mathrock.model.User;
import com.po.mathrock.repositories.RoleRepository;
import com.po.mathrock.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Qualifier("UserServiceImpl")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final String USER_ROLE = "USER";

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepsitory, RoleRepository roleRepository) {
        this.userRepository = userRepsitory;
        this.roleRepository = roleRepository;
    }

    // Return custom implementetations for Spring Security's UserDetailsService Interface
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsImpl(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public String registerUser(User newUser) {
        User userCheck = userRepository.findByUsername(newUser.getUsername());

        if(userCheck != null) {
            System.out.println("ALREADY EXISTS!!!");
            return "alreadyExists";
        }

        System.out.println("Registering " + newUser.getUsername());

        Role userRole = roleRepository.findByRoleName(USER_ROLE);
        System.out.println("USERROLES " + newUser.getUsername() + " " + userRole);
        if(userRole != null) {
            newUser.getUserRoles().add(userRole);
            System.out.println("ADDING DB Role " + newUser.getUsername() + " " + userRole);
        } else {
            newUser.addRole(USER_ROLE);
        }
        userRepository.save(newUser);

        System.out.println("OK!!!");
        return "ok";
    }


    @Override
    public User addNewRole(User user, String newRoleName) {
        for (Role roleToCheck : user.getUserRoles()) {
            if(roleToCheck.getRoleName().equals(newRoleName)) {
                System.out.println("This user already has this role: " + newRoleName);
                return null;
            }
        }
        Role roleInDatabase = roleRepository.findByRoleName(newRoleName);
        System.out.println("IN DataBase! " + roleInDatabase);
        if(roleInDatabase != null) {
//            user.getUserRoles().add(roleInDatabase);
//            Role newRole = new Role(newRoleName);
//            roleRepository.saveAndFlush(newRole);
//            roleRepository.saveAndFlush(roleInDatabase);
            user.getUserRoles().add(roleInDatabase);
        } else {
            Role newRole = new Role(newRoleName);
//            roleRepository.saveAndFlush(newRole);
            user.getUserRoles().add(newRole);
        }
        roleRepository.saveAndFlush(roleInDatabase);
        return user;
    }

    @Override
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }
}
