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

    private List<Lesson> lessons = new ArrayList<Lesson>();

    String name;

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


    public TeacherDto() {
    }

    public TeacherDto(String name) {
        this.name = name;
    }
}
