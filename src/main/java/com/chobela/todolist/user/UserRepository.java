package com.chobela.todolist.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<TodoUser, Integer> {

    Optional<TodoUser> findByUsername(String username);
}
