package com.example.trainproject.dao;

import com.example.trainproject.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
    Role getRoleByName(String name);
}
