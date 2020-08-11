package com.spring.boot.project.demo.service.impl;

import com.spring.boot.project.demo.model.UsersRole;
import com.spring.boot.project.demo.repository.UsersRoleRepository;
import com.spring.boot.project.demo.service.UsersRoleService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsersRoleServiceImpl implements UsersRoleService {
    private UsersRoleRepository usersRoleRepository;

    @Override
    public UsersRole save(UsersRole usersRole) {
        return usersRoleRepository.save(usersRole);
    }

    @Override
    public List<UsersRole> saveAll(List<UsersRole> listT) {
        return usersRoleRepository.saveAll(listT);
    }

    @Override
    public UsersRole findById(Long id) {
        return usersRoleRepository.findById(id).orElseThrow();
    }

    @Override
    public List<UsersRole> findAll() {
        return usersRoleRepository.findAll();
    }

    @Override
    public void delete(UsersRole usersRole) {
        usersRoleRepository.delete(usersRole);
    }

    @Override
    public void deleteById(Long id) {
        usersRoleRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        usersRoleRepository.deleteAll();
    }

    @Override
    public void deleteAll(Iterable<UsersRole> iterable) {
        usersRoleRepository.deleteAll(iterable);
    }

    @Override
    public UsersRole getRoleByName(String user) {
        UsersRole.RoleName roleName = UsersRole.RoleName.valueOf(user);
        return usersRoleRepository.findByRoleName(roleName);
    }
}
