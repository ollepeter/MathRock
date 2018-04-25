package com.po.mathrock.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="roles")
public class Role {



    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="role_id")
    private Integer id;

    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> roleUsers;


    public Role() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getRoleUsers() {
        return roleUsers;
    }

    public void setRoleUsers(Set<User> roleUsers) {
        if(this.roleUsers == null || this.roleUsers.isEmpty()) {
            this.roleUsers = new HashSet<>();
        }

        this.roleUsers = roleUsers;
    }
}
