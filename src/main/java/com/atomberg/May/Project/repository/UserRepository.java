package com.atomberg.May.Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atomberg.May.Project.entity.User;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
}
