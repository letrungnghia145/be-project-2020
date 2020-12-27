package com.nghiale.api.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nghiale.api.model.User;

public interface UserEntity<Entity extends User> extends JpaRepository<Entity, Long> {
}
