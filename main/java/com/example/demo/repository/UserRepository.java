package com.example.demo.repository;

import com.example.demo.entities.MyUser;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  UserRepository extends JpaRepository<MyUser,Long> {

    Optional<MyUser> findByName(String userName);
}
