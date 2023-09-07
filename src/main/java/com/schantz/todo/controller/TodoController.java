package com.schantz.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schantz.todo.dto.TodoDto;
import com.schantz.todo.service.TodoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {
	
	private TodoService todoService;
	
	//build add todo rest api
	@PostMapping
	public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
		TodoDto savedTodo = todoService.addTodo(todoDto);
		return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
	}
	
	//build get todo rest api
	@GetMapping("{id}")
	public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId) {
		TodoDto todoDto = todoService.getTodo(todoId);
		
		return new ResponseEntity<>(todoDto, HttpStatus.OK);
	}
	
	//Buil getAll todo rest api
	@GetMapping
	public ResponseEntity<List<TodoDto>> getAllTodos(){
		List<TodoDto> todos = todoService.getAllTodos();
		//return new ResponseEntity<>(todos, HttpStatus.OK);
		return ResponseEntity.ok(todos);
	}
	
}
