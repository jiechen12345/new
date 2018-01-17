package com.example.demo.dto;

import com.example.demo.entity.Lesson;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiechen on 2017/10/7.
 */

public class TeacherDto {

    public TeacherDto() {
    }

    public TeacherDto(String name) {
        this.name = name;
    }

    public TeacherDto(List<Lesson> lessons, String name, String id) {
        this.lessons = lessons;
        this.name = name;
        this.id = id;
    }

    private List<Lesson> lessons = new ArrayList<Lesson>();

    String name;

    String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
