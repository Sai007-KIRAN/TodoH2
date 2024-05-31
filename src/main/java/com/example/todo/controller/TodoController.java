package com.example.todo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

import com.example.todo.service.TodoH2Service;
import com.example.todo.model.Todo;

@RestController

public class TodoController {
    @Autowired
    public TodoH2Service ths;

    @GetMapping("/todos")
    public ArrayList<Todo> allTodo() {
        return ths.allTodo();
    }

    @GetMapping("/todos/{id}")
    public Todo eachTodo(@PathVariable("id") int id) {
        return ths.eachTodo(id);
    }

    @PostMapping("/todos")
    public Todo addTodo(@RequestBody Todo to) {
        return ths.addTodo(to);
    }

    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable("id") int id, @RequestBody Todo Td) {
        return ths.updateTodo(id, Td);
    }

    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable int id) {
        this.deleteTodo(id);
    }
}
