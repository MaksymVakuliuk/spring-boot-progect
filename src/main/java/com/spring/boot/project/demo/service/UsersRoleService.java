package com.spring.boot.project.demo.service;

import com.spring.boot.project.demo.model.UsersRole;

public interface UsersRoleService extends GenericService<UsersRole, Long> {
    UsersRole getRoleByName(String user);
}
