package com.schantz.todo.service;

import java.util.List;

import com.schantz.todo.dto.TodoDto;

public interface TodoService {
	TodoDto addTodo(TodoDto todoDto);
	TodoDto getTodo(Long id);
	List<TodoDto> getAllTodos();
}
