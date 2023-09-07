package com.schantz.todo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schantz.todo.dto.TodoDto;
import com.schantz.todo.entity.Todo;
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
		/*Todo todo = new Todo();
		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setCompleted(todoDto.isCompleted());*/
		
		Todo todo = modelMapper.map(todoDto, Todo.class);
		
		// Todo Jpa Entity
		Todo savedTodo = todoRepository.save(todo);		
		
		//Convert saved todo jpa entity object into todoDto object
		/*TodoDto saveTodoDto = new TodoDto();
		saveTodoDto.setId(savedTodo.getId());
		saveTodoDto.setTitle(savedTodo.getTitle());
		saveTodoDto.setDescription(savedTodo.getDescription());
		saveTodoDto.setCompleted(savedTodo.isCompleted());*/
		
		TodoDto saveTodoDto = modelMapper.map(savedTodo, TodoDto.class);
		
		return saveTodoDto;
	}

}
