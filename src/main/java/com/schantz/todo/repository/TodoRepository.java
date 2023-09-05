package com.schantz.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schantz.todo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

}
