package com.normal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.normal.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);

	boolean existsByUsername(String username);

}
