// Write your code here

package com.example.todo.repository;

import java.util.*;
import com.example.todo.model.Todo;

public interface TodoRepository {

    ArrayList<Todo> allTodo();

    Todo eachTodo(int id);

    Todo addTodo(Todo to);

    Todo updateTodo(int id, Todo Td);

    void deleteTodo(int id);
}
