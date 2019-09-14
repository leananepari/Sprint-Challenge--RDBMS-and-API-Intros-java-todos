package com.lambdaschool.todos.services;


import com.lambdaschool.todos.model.Todo;
import com.lambdaschool.todos.model.User;
import com.lambdaschool.todos.model.UserRoles;
import com.lambdaschool.todos.repository.RoleRepository;
import com.lambdaschool.todos.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService
{
    @Autowired
    TodoRepository todorepos;

    @Autowired
    private TodoService todoService;


    @Override
    public Todo findTodoById(long id)
    {
        return todorepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
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
    public Todo update(Todo updateTodo, long id) {

        Todo todo = todorepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (updateTodo.getDatestarted() != null)
        {
            todo.setDatestarted(updateTodo.getDatestarted());
        }
        if (updateTodo.getDescription() != null)
        {
            todo.setDescription(updateTodo.getDescription());
        }
        if (updateTodo.getCompleted() != false)
        {
            todo.setCompleted(updateTodo.getCompleted());
        }
        return todorepos.save(todo);
    }
}
