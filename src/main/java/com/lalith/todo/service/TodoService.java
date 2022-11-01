package com.lalith.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lalith.todo.model.Todo;
import com.lalith.todo.repo.TodoRepository;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;
	
	public Todo getTodoById(Long id) {
		return todoRepository.findById(id).orElse(null);
	}
	
	public List<Todo> getTodos() {
		return todoRepository.findAll();
	}
	
	public Todo addTodo(Todo todo) {
		try {
			return todoRepository.save(todo);
		} catch (Exception e) {
			return null;
		}	
	}
	
	public boolean updateTodo(Long id, Todo todo) {
		try {
			if(this.getTodoById(id) == null)
				return false;
			return todoRepository.save(todo) != null;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public boolean deleteTodo(Long id) {
		try {
			if(this.getTodoById(id) != null)
			todoRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			System.out.println("error: " + e);
			return false;
		}	
	}
}
