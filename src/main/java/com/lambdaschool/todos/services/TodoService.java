package com.lambdaschool.todos.services;

import com.lambdaschool.todos.model.Role;
import com.lambdaschool.todos.model.Todo;
import com.lambdaschool.todos.model.User;

public interface TodoService
{
    Todo findTodoById(long id);

    void delete(long id);

    Todo update(Todo updateTodo, long id);

    Todo save(Todo todo);
}
