package com.po.mathrock.repositories;

import com.po.mathrock.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRoleName(String role);
}
