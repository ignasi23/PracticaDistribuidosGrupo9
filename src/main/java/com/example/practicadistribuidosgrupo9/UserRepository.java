package com.example.practicadistribuidosgrupo9;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserName(String userName);

    boolean existsByUserName(String userName);
    void deleteByUserName(String userName);
}
