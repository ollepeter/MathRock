package com.po.mathrock.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="user_id")
    private Integer userID;

    @Column(nullable=false)
    private String username;

    @Column(nullable=false)
    private String password;


    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name="user_roles",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")}
            )
    private Set<Role> userRoles = new HashSet<>();


    public User () {

    }

    public User (String username, String password) {
        this.username = username;
        this. password = password;
    }

    public Integer getId() {
        return userID;
    }

    public void setId(Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<Role> userRoles) {
        if(this.userRoles == null || this.userRoles.isEmpty()) {
            this.userRoles = new HashSet<>();
        }
        this.userRoles = userRoles;
    }


    public void addRoles(Role role) {
        if(this.userRoles == null || this.userRoles.isEmpty()) {
            this.userRoles = new HashSet<>();
        }
        this.userRoles.add(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
