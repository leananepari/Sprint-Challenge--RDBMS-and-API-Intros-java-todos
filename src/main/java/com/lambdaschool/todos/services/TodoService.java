package com.lambdaschool.todos.services;

import com.lambdaschool.todos.model.Role;
import com.lambdaschool.todos.model.Todo;
import com.lambdaschool.todos.model.User;

public interface TodoService
{
    Todo findTodoById(long id);

    void delete(long id);

    void update(Todo updateTodo, Todo todo);

    Todo save(Todo todo);
}
