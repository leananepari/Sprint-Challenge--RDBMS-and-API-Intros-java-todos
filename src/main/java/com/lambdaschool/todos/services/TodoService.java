package com.lambdaschool.todos.services;

import com.lambdaschool.todos.model.Role;
import com.lambdaschool.todos.model.Todo;

public interface TodoService
{
    Todo fondTodoById(long id);

    void delete(long id);

    Todo save(Todo todo);
}
