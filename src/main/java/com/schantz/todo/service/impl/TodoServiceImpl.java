package com.schantz.todo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

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
public class TodoServiceImpl implements TodoService {

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

		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));

		return modelMapper.map(todo, TodoDto.class);
	}

	@Override
	public List<TodoDto> getAllTodos() {

		List<Todo> todos = todoRepository.findAll();

		return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList());
	}

	@Override
	public TodoDto updateTodo(TodoDto todoDto, Long id) {

		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setCompleted(todoDto.isCompleted());

		Todo updateTodo = todoRepository.save(todo);

		return modelMapper.map(updateTodo, TodoDto.class);
	}

	@Override
	public void deleteTodo(Long id) {

		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found id: " + id));
		todoRepository.deleteById(id);
	}

	@Override
	public TodoDto completeTodo(Long id) {
		Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Todo not found with id: "+ id));
		todo.setCompleted(Boolean.TRUE);
		Todo updateTodo = todoRepository.save(todo);
		return modelMapper.map(updateTodo, TodoDto.class);
	}

}
