package com.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
