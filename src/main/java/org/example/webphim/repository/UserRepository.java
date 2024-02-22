package org.example.webphim.repository;

import org.example.webphim.entity.User;
import org.example.webphim.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRole(UserRole role);
}
