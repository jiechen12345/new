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

    String id;

    String name;

    private List<String> lessons = new ArrayList<String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLessons() {
        return lessons;
    }

    public void setLessons(List<String> lessons) {
        this.lessons = lessons;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TeacherDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lessons=" + lessons +
                '}';
    }

}
