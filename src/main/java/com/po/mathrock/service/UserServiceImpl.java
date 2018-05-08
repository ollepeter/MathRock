package com.po.mathrock.service;

import com.po.mathrock.model.Role;
import com.po.mathrock.model.User;
import com.po.mathrock.repositories.RoleRepository;
import com.po.mathrock.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final String USER_ROLE = "USER";

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepsitory, RoleRepository roleRepository) {
        this.userRepository = userRepsitory;
        this.roleRepository = roleRepository;
    }

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
    public void registerUser(User newUser) {
        Role userRole = roleRepository.findByRoleName(USER_ROLE);
        if(userRole != null) {
            newUser.getUserRoles().add(userRole);
        } else {
            newUser.addRoles(new Role(USER_ROLE));
        }
        userRepository.save(newUser);
    }

    @Override
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void saveNewUser(User newUser) {
        User userInDataBase = userRepository.findByUsername(newUser.getUsername());
        if(userInDataBase == null) {
            userRepository.save(newUser);
        } else {
            System.out.println("USER ALREADY IN DATABASE");
        }
    }


    @Override
    public void addNewRole(User user, Role role) {
        User userInDataBase = userRepository.findByUsername(user.getUsername());
        if(userInDataBase != null) {
            user = userInDataBase;
        } else {
            userRepository.save(user);
        }
        Role roleInDataBase = roleRepository.findByRoleName(role.getRoleName());
        if(roleInDataBase != null) {
            role = roleInDataBase;
        }
        if(!userHasRole(user,role)) {
            if(roleInDataBase == null) {
                roleRepository.save(role);
                user.addRoles(role);
                userRepository.save(user);
            } else {
                roleRepository.save(roleInDataBase);
                user.addRoles(roleInDataBase);
                userRepository.save(user);
            }
        }
    }

    boolean userHasRole(User user, Role roleToCheck) {
        for (Role role : user.getUserRoles()) {
            if(role.getRoleName().equals(roleToCheck.getRoleName())) {
                return true;
            }
        }
        return false;
    }
}
