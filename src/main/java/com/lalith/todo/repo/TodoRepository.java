package com.lalith.todo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lalith.todo.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
