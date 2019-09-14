package com.lambdaschool.todos;
// provided by Vivek Vishwanath

import com.lambdaschool.todos.model.Role;
import com.lambdaschool.todos.model.Todo;
import com.lambdaschool.todos.model.User;
import com.lambdaschool.todos.model.UserRoles;
import com.lambdaschool.todos.repository.RoleRepository;
import com.lambdaschool.todos.repository.TodoRepository;
import com.lambdaschool.todos.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

@Transactional
@Component
public class SeedData implements CommandLineRunner {
    RoleRepository rolerepos;
    UserRepository userrepos;
    TodoRepository todorepos;

    public SeedData(RoleRepository rolerepos, UserRepository userrepos, TodoRepository todorepos) {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
        this.todorepos = todorepos;
    }

    @Override
    public void run(String[] args) throws Exception {
        Role r1 = new Role("ADMIN");
        Role r2 = new Role("user");

        r1 = rolerepos.save(r1);
        r2 = rolerepos.save(r2);

        ArrayList<UserRoles> users = new ArrayList<>();
//        users.add(new UserRoles(new User(), r2));
        User u1 = new User("barnbarn", "ILuvM4th!", users);
        Todo todo1 = new Todo("Finish java-orders-swagger", new Date(), false);
        u1.getTodos().add(todo1);
        todo1.setUser(u1);
        Todo todo2 = new Todo("Feed the turtles", new Date(), false);
        u1.getTodos().add(todo2);
        todo2.setUser(u1);
        Todo todo3 = new Todo("Complete the sprint challenge", new Date(), false);
        u1.getTodos().add(todo3);
        todo3.setUser(u1);
        userrepos.save(u1);

        ArrayList<UserRoles> admins = new ArrayList<>();
//        admins.add(new UserRoles(new User(), r1));
//        admins.add(new UserRoles(new User(), r2));
        User u2 = new User("admin", "password", admins);
        Todo todo4 = new Todo("Walk the dogs", new Date(), false);
        u2.getTodos().add(todo4);
        todo4.setUser(u2);
        Todo todo5 = new Todo("provide feedback to my instructor", new Date(), false);
        u2.getTodos().add(todo5);
        todo5.setUser(u2);
        userrepos.save(u2);

        users = new ArrayList<>();
//        users.add(new UserRoles(new User(), r2));
        User u3 = new User("Bob", "password", users);
        userrepos.save(u3);

        users = new ArrayList<>();
//        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Jane", "password", users);
        userrepos.save(u4);
    }
}