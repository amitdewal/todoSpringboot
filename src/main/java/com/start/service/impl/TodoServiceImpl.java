package com.start.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.dto.TodoDto;
import com.start.entity.Todo;
import com.start.exception.ResourceNotFoundException;
import com.start.repository.TodoRepository;
import com.start.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private TodoRepository todoRepository;

	@Override
	public TodoDto addTodo(TodoDto todoDto) {
		
		//convert TodoDto into todo entity
		Todo todo = modelMapper.map(todoDto, Todo.class);
		
		//saving to db
		Todo savedTodo = todoRepository.save(todo);
		
		//convert entity todo into DTO todo 
		TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);
		
		
		return savedTodoDto;
	}

	@Override
	public TodoDto getTodo(Long id) throws Exception {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with given id "+ id));

		TodoDto savedTodoDto = modelMapper.map(todo, TodoDto.class);
		
		return savedTodoDto;
	}

	@Override
	public List<TodoDto> getAllTodos() {
		List<Todo> todoList = todoRepository.findAll();
		
		List<TodoDto> todoDtoList = todoList
											.stream()
											.map((todo) -> modelMapper.map(todo,TodoDto.class))
											.collect(Collectors.toList());
		return todoDtoList;
	}

	@Override
	public List<TodoDto> getAllTodosDistinct() {
		List<Todo> todoList = todoRepository.findAll();
		return null;
		



}

	@Override
	public TodoDto updateTodo(TodoDto todoDto, Long id) {
		Todo todo = todoRepository.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("Todo not found with given id "+ id));
		
		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setCompleted(todoDto.isCompleted());
		
		Todo updatedTodo = todoRepository.save(todo);
		TodoDto updatedTodoDto = modelMapper.map(updatedTodo, TodoDto.class);
		
		return updatedTodoDto;
	}

	@Override
	public void deleteTodo(Long id) {
		Todo todo = todoRepository.findById(id)
						.orElseThrow(()-> new ResourceNotFoundException("Todo not found with given id "+ id));
		todoRepository.deleteById(id);
		
	}

	@Override
	public TodoDto completeTodo(Long id) {
		Todo todo = todoRepository.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("Todo not found with given id "+ id));
		
		todo.setCompleted(Boolean.TRUE);//
		
		Todo saveTodo = todoRepository.save(todo);
		return modelMapper.map(saveTodo, TodoDto.class);
	}

	@Override
	public TodoDto isCompleteTodo(Long id) {
		Todo todo = todoRepository.findById(id)
									.orElseThrow(()-> new ResourceNotFoundException("Todo not found with given id "+ id));
		todo.setCompleted(Boolean.FALSE);//
		Todo saveTodo = todoRepository.save(todo);
		return modelMapper.map(saveTodo, TodoDto.class);
	}
	

}
