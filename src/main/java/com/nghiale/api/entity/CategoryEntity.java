package com.nghiale.api.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nghiale.api.model.Category;

public interface CategoryEntity extends JpaRepository<Category, Long> {

}
