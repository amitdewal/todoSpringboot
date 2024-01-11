package com.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.start.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
