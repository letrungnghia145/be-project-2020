package com.nghiale.api.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nghiale.api.model.User;

public interface UserEntity<Entity extends User> extends JpaRepository<Entity, Long> {
	public Optional<Entity> findByUserCode(String userCode);

	@Query("SELECT count(u)>0 FROM User u WHERE u.userCode = ?1")
	public boolean isExistingUserCode(String userCode);

	@Query("SELECT count(u)>0 FROM User u WHERE u.email = ?1")
	public boolean isExistingUserEmail(String email);
}
