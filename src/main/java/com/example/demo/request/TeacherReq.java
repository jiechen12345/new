package com.example.demo.request;

import com.example.demo.entity.Lesson;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiechen on 2017/10/7.
 */

public class TeacherReq {

    private List<String> lessons = new ArrayList<String>();


    String id;

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLessons() {
        return lessons;
    }
    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public void setLessons(List<String> lessons) {
        this.lessons = lessons;
    }


    public TeacherReq() {
    }

    public TeacherReq(String name) {
        this.name = name;
    }

    public TeacherReq(List<String> lessons, String name) {
        this.lessons = lessons;
        this.name = name;
    }

    public TeacherReq(List<String> lessons, String id, String name) {
        this.lessons = lessons;
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "TeacherReq{" +
                "lessons=" + lessons +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
