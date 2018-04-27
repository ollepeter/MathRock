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

    private String roleName;

    @ManyToMany(mappedBy = "userRoles")
    private Set<User> roleUsers;


    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
