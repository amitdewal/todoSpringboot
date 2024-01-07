package com.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.start.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
		
}
