package com.lambdaschool.todos.services;


import com.lambdaschool.todos.model.Todo;
import com.lambdaschool.todos.repository.RoleRepository;
import com.lambdaschool.todos.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService
{
    @Autowired
    TodoRepository todorepos;

    @Override
    public Todo fondTodoById(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Todo save(Todo todo)
    {
        return null;
    }
}
