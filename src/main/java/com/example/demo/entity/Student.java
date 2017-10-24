package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiechen on 2017/10/7.
 */
@Entity
public class Student {
    @Id
    String id;

    @ManyToMany()
    private List<Lesson> lessons = new ArrayList<Lesson>();

    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }


    public Student() {
    }

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
