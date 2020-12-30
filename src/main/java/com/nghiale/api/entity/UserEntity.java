package com.nghiale.api.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nghiale.api.model.User;

public interface UserEntity<Entity extends User> extends JpaRepository<Entity, Long> {
	public Optional<Entity> findByUserCode(String userCode);
}
