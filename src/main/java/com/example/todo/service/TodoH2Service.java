package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import org.springframework.dao.EmptyResultDataAccessException;
import com.example.todo.model.Todo;
import com.example.todo.model.TodoRowMapper;
import com.example.todo.repository.TodoRepository;

// Write your code here

@Service
public class TodoH2Service implements TodoRepository {
    @Autowired
    public JdbcTemplate jd;

    @Override
    public ArrayList<Todo> allTodo() {
        List<Todo> alltos = jd.query("SELECT * FROM TODOLIST", new TodoRowMapper());
        ArrayList<Todo> AllTodos = new ArrayList<>(alltos);
        return AllTodos;
    }

    @Override
    public Todo eachTodo(int id) {
        try {
            Todo Todoeach = jd.queryForObject("SELECT * FROM TODOLIST WHERE id = ?", new TodoRowMapper(), id);
            return Todoeach;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Todo addTodo(Todo to) {
        jd.update("INSERT INTO TODOLIST(todo, status, priority) VALUES(?, ?, ?)", to.getTodo(), to.getStatus(),
                to.getPriority());
        Todo AddingTodo = jd.queryForObject("Select * From TODOLIST WHERE todo = ? AND status = ? AND priority = ?",
                new TodoRowMapper(), to.getTodo(), to.getStatus(), to.getPriority());
        return AddingTodo;
    }

    @Override
    public Todo updateTodo(int id, Todo Td) {
        try {
            Todo existing = jd.queryForObject("SELECT * FROM TODOLIST WHERE id = ?", new TodoRowMapper(),
                    id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (Td.getTodo() != null) {
            jd.update("UPDATE TODOLIST SET todo = ? WHERE id = ?", Td.getTodo(), id);
        }
        if (Td.getStatus() != null) {
            jd.update("UPDATE TODOLIST SET status = ? WHERE id = ?", Td.getStatus(), id);
        }
        if (Td.getPriority() != null) {
            jd.update("UPDATE TODOLIST SET priority = ? WHERE id = ?", Td.getPriority(), id);
        }
        return eachTodo(id);
    }

    @Override
    public void deleteTodo(int id) {
        jd.update("DELETE FROM TODOLIST WHERE id = ?", id);
    }

}