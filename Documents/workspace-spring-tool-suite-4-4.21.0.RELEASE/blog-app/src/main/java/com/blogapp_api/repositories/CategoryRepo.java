package com.blogapp_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp_api.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
