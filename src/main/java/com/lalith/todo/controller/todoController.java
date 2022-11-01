package com.lalith.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lalith.todo.model.Todo;
import com.lalith.todo.service.TodoService;

@RestController
public class todoController {

	@Autowired
	private TodoService todoService;
	
	// To Add a TODO
	@PostMapping("/todos")
	public ResponseEntity<?> addTodoById(@RequestBody Todo todo) {
		Todo todoObj = todoService.addTodo(todo);
		
		if(todoObj == null)
			return	new ResponseEntity<String>("Todo item not saved", HttpStatus.BAD_REQUEST);
		
		return	new ResponseEntity<Todo>(todoObj, HttpStatus.CREATED);
	}
	
	// To Get a TODO by ID
	@GetMapping("/todos")
	public ResponseEntity<List<Todo>> getTodos() {

		return	new ResponseEntity<List<Todo>>(todoService.getTodos(), HttpStatus.OK);
	}
		
	// To Get a TODO by ID
	@GetMapping("/todos/{id}")
	public ResponseEntity<?> getTodoById(@PathVariable Long id) {
		Todo todo = todoService.getTodoById(id);
		
		if(todo == null)
			return	new ResponseEntity<String>("Todo Item doesn't exist with given ID", HttpStatus.NOT_FOUND);
		else
			return	new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}
	
	@PutMapping("/todos/{id}")
	public ResponseEntity<?> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
		if(todoService.updateTodo(id, todo))
			return new ResponseEntity<Todo>(todoService.getTodoById(id), HttpStatus.OK);
		else
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	// To Delete a TODO by ID
	@DeleteMapping("/todos/{id}")
	public HttpStatus deleteTodoById(@PathVariable long id) {
		if(todoService.deleteTodo(id))
			return HttpStatus.NO_CONTENT;
		
		return	HttpStatus.INTERNAL_SERVER_ERROR;
	}
	
	
}
