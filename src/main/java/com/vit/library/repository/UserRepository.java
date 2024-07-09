package com.vit.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vit.library.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    
}
