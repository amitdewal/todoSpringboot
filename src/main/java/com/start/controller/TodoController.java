package com.start.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.JsonPath;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.start.dto.TodoDto;
import com.start.service.TodoService;

@RestController
@RequestMapping("api/todos")//base url
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	
	//add todo rest api
	
	@PreAuthorize("hasRole('ADMIN')")//method level authority
	@PostMapping()
	public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
		
		TodoDto savedTodo = todoService.addTodo(todoDto);
		
		return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
	}
	
	//get todo rest api
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("{id}")
	public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId) throws Exception{
		TodoDto todoDto = todoService.getTodo(todoId);
		return new ResponseEntity<TodoDto>(todoDto, HttpStatus.OK);
		
	}
	
	//get all todos rest api
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping
	public ResponseEntity<List<TodoDto>> getAllTodos(){
		List<TodoDto> allTodos = todoService.getAllTodos();
		
		return  ResponseEntity.ok(allTodos);
		
	}
	
	
	
	@PutMapping("{id}")
	public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,@PathVariable("id") Long id){
		
		TodoDto updateTodo = todoService.updateTodo(todoDto, id);
		return ResponseEntity.ok(updateTodo);
	}
	@PreAuthorize("hasRole('ADMIN')")//method level authority
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id){
		todoService.deleteTodo(id);
		return ResponseEntity.ok("todo deleted successfully");
	}
	//build complete todo rest api
	
	@PatchMapping("{id}/complete")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId){
		TodoDto updatedTodo = todoService.completeTodo(todoId);
		return ResponseEntity.ok(updatedTodo);
	}
	
	@PatchMapping("{id}/in-complete")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long todoId){
		TodoDto updatedTodo = todoService.isCompleteTodo(todoId);
		return ResponseEntity.ok(updatedTodo);
	}

}
