package com.lambdaschool.todos.services;

import com.lambdaschool.todos.model.Role;
import com.lambdaschool.todos.model.Todo;
import com.lambdaschool.todos.model.User;
import com.lambdaschool.todos.model.UserRoles;
import com.lambdaschool.todos.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService
{
    @Autowired
    RoleRepository rolerepos;

    @Override
    public List<Role> findAll()
    {
        List<Role> list = new ArrayList<>();
        rolerepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


    @Override
    public Role findRoleById(long id)
    {
        return rolerepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }


    @Override
    public void delete(long id)
    {
        rolerepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        rolerepos.deleteById(id);
    }


    @Transactional
    @Override
    public Role save(Role role)
    {
//        Role newRole = new Role();
//        newRole.setName(role.getName());

        return rolerepos.save(role);
    }
}
