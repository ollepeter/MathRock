package com.po.mathrock.repositories;

import com.po.mathrock.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Integer> {
    Role findByRoleName(String role);
}
