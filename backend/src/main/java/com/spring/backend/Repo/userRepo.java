package com.spring.backend.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.backend.Entity.userEntity;

@Repository
public interface userRepo extends JpaRepository<userEntity, Long>{

    userEntity findByEmail(String email);

    userEntity findByName(String name);

    @Query(value = "Select * from users", nativeQuery = true)
    List<userEntity> listUsers();
}
