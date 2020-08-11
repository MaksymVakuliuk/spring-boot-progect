package com.spring.boot.project.demo.repository;

import com.spring.boot.project.demo.model.UsersRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRoleRepository extends JpaRepository<UsersRole, Long> {

    UsersRole findByRoleName(UsersRole.RoleName roleName);
}
