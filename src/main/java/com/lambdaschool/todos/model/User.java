package com.lambdaschool.todos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @Column(nullable = false,
            unique = true)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<Todo> todos = new ArrayList<>();

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<UserRoles> userRoles = new ArrayList<>();

    public User()
    {
    }

    public User(String username, String password, List roles) {
        this.username = username;
        this.password = password;
        this.userRoles = roles;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }
    public void setPasswordNoEncrypt(String password)
    {
        this.password = password;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    public List<UserRoles> getRoles() {
        return userRoles;
    }

    public void setRoles(List<UserRoles> roles) {
        this.userRoles = roles;
    }

    public List<SimpleGrantedAuthority> getAuthority()
    {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

        for (UserRoles r : this.userRoles)
        {
            String myRole = "ROLE_" + r.getRole().getName().toUpperCase();
            rtnList.add(new SimpleGrantedAuthority(myRole));
        }
        return rtnList;
    }
}
