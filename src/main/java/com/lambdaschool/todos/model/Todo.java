package com.lambdaschool.todos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.h2.util.DateTimeUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "todos")
public class Todo
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoid;

    @Column(nullable = false)
    private String description;

    private Date datestarted;
    private Boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties("todos")
    private User user;

    public Todo() {
    }

    public Todo(String description, Date datestarted, User user) {
        this.description = description;
        this.datestarted = datestarted;
        this.user = user;
    }

    public long getTodoid() {
        return todoid;
    }

    public void setTodoid(long todoid) {
        this.todoid = todoid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatestarted() {
        return datestarted;
    }

    public void setDatestarted(Date datestarted) {
        this.datestarted = datestarted;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
