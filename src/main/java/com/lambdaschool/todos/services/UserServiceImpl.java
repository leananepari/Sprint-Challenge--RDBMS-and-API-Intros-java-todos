package com.lambdaschool.todos.services;



import com.lambdaschool.todos.model.Todo;
import com.lambdaschool.todos.model.User;
import com.lambdaschool.todos.model.UserRoles;
import com.lambdaschool.todos.repository.RoleRepository;
import com.lambdaschool.todos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService
{

    @Autowired
    private UserRepository userrepos;

    @Autowired
    private RoleRepository rolerepos;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userrepos.findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthority());
    }

    @Transactional
    @Override
    public User findUserById(long id) throws EntityNotFoundException
    {
        return userrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        userrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        if (userrepos.findById(id).isPresent())
        {
            userrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public User save(User user)
    {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPasswordNoEncrypt(user.getPassword());

        ArrayList<UserRoles> newRoles = new ArrayList<>();
        for (UserRoles ur : user.getRoles())
        {
            newRoles.add(new UserRoles(newUser, ur.getRole()));
        }
        newUser.setRoles(newRoles);

        for (Todo t : user.getTodos())
        {
            Todo todo = new Todo(t.getDescription(), t.getDatestarted(), t.getCompleted());
            todo.setUser(newUser);
            newUser.getTodos().add(todo);
        }

        return userrepos.save(newUser);
    }

    @Transactional
    @Override
    public User findUserByName(String name)
    {
        User currentUser = userrepos.findByUsername(name);

        if (currentUser != null)
        {
            return currentUser;
        } else
        {
            throw new EntityNotFoundException(name);
        }
    }

    @Transactional
    @Override
    public User update(User user, long id)
    {
        User currentUser = userrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (user.getUsername() != null)
        {
            currentUser.setUsername(user.getUsername());
        }

        if (user.getPassword() != null)
        {
            currentUser.setPasswordNoEncrypt(user.getPassword());
        }

        if (user.getRoles().size() > 0)
        {
            // with so many relationships happening, I decided to go
            // with old school queries
            // delete the old ones
            rolerepos.deleteUserRolesByUserId(currentUser.getUserid());

            // add the new ones
            for (UserRoles ur : user.getRoles())
            {
                rolerepos.insertUserRoles(id, ur.getRole().getRoleid());
            }
        }

        if (user.getTodos().size() > 0)
        {
            for (Todo t : user.getTodos())
            {
                Todo todo = new Todo(t.getDescription(), t.getDatestarted(), t.getCompleted());
                todo.setUser(currentUser);
                currentUser.getTodos().add(todo);
            }
        }
        return userrepos.save(currentUser);
    }
}