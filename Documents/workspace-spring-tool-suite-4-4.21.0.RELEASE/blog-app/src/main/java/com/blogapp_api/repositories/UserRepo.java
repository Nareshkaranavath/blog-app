package com.blogapp_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp_api.entities.User;

public interface UserRepo extends JpaRepository<User,Integer>{

}
