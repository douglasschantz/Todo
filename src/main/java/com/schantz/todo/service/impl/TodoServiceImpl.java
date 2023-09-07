package com.schantz.todo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schantz.todo.dto.TodoDto;
import com.schantz.todo.entity.Todo;
import com.schantz.todo.exception.ResourceNotFoundException;
import com.schantz.todo.repository.TodoRepository;
import com.schantz.todo.service.TodoService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService{
	
	private TodoRepository todoRepository;
	
	private ModelMapper modelMapper;
	
	@Override
	public TodoDto addTodo(TodoDto todoDto) {
		// Convert TodoDto in Todo Jpa Entity
		Todo todo = modelMapper.map(todoDto, Todo.class);
		
		// Todo Jpa Entity
		Todo savedTodo = todoRepository.save(todo);			
		
		TodoDto saveTodoDto = modelMapper.map(savedTodo, TodoDto.class);
		
		return saveTodoDto;
	}

	@Override
	public TodoDto getTodo(Long id) {
		
		Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: "+id));	
		
		return modelMapper.map(todo, TodoDto.class);
	}

}
