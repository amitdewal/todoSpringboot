package com.start.service;

import java.util.List;

import com.start.dto.TodoDto;

public interface TodoService {
	
	TodoDto addTodo(TodoDto todoDto);
	TodoDto getTodo(Long id) throws Exception;
	List<TodoDto> getAllTodos();
	List<TodoDto> getAllTodosDistinct();
	TodoDto updateTodo(TodoDto todoDto,Long id);
	void deleteTodo(Long id);
	TodoDto completeTodo(Long id);
	TodoDto isCompleteTodo(Long id);
}
