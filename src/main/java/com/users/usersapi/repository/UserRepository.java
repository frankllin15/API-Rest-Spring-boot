package com.users.usersapi.repository;


import com.users.usersapi.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    User findById(long id);
}


