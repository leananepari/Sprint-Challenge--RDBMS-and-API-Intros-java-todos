package com.lambdaschool.todos.services;


import com.lambdaschool.todos.model.Todo;
import com.lambdaschool.todos.model.User;
import com.lambdaschool.todos.model.UserRoles;
import com.lambdaschool.todos.repository.RoleRepository;
import com.lambdaschool.todos.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService
{
    @Autowired
    TodoRepository todorepos;

    @Override
    public Todo findTodoById(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Todo save(Todo todo)
    {
        return todorepos.save(todo);
    }

    @Override
    public void update(Todo updateTodo, Todo todo) {
//        if (updateTodo.getCompleted() != null)
//        {
//            todo.setCompleted(updateTodo.getCompleted());
//        }
//        if (updateTodo.getDatestarted() != null)
//        {
//            todo.setDatestarted(updateTodo.getDatestarted());
//        }
//        if (updateTodo.)
//        todo.setDescription(updateTodo.getDescription());
//        todo.setDatestarted(updateTodo.getDatestarted());
    }
}
