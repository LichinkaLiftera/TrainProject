package com.example.trainproject.service;

import com.example.trainproject.dao.RoleDao;
import com.example.trainproject.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao){
        this.roleDao = roleDao;

    }
    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }
    @Override
    public List<Role> getAllRoles() {
        return roleDao.findAll();
    }


}
