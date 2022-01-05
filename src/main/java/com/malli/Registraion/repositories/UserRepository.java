package com.malli.Registraion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malli.Registraion.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
