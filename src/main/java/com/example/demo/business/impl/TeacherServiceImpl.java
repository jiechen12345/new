package com.example.demo.business.impl;

import com.example.demo.business.TeacherService;
import com.example.demo.dao.LessonDao;
import com.example.demo.dao.TeacherDao;
import com.example.demo.dto.TeacherDto;
import com.example.demo.entity.Lesson;
import com.example.demo.entity.Teacher;
import com.example.demo.request.TeacherReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by jiechen on 2018/1/14.
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private LessonDao lessonDao;

    @Override
    public List<TeacherDto> findAll() {
        List<TeacherDto> teacherDtos = teacherDao.findAll().stream()
                .map(this::getTeacherDto)
                .collect(Collectors.toList());
        return teacherDtos;
    }

    private TeacherDto getTeacherDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setName(teacher.getName());
        teacherDto.setId(teacher.getId());
        teacherDto.setLessons(teacher.getLessons());
        if (teacher.getLessons() == null) {
            List<Lesson> lessons = new ArrayList<>();
            for (Lesson it : teacher.getLessons()) {
                lessons.add(it);
            }
            teacherDto.setLessons(lessons);
        }
        return teacherDto;
    }

    @Override
    public void creat(TeacherReq teacherReq) {
        Teacher teacher = new Teacher();
        teacher.setId(teacherReq.getid());
        teacher.setName(teacherReq.getName());
        List<Lesson> lessons = teacherReq.getLessons().stream()
                .map(it -> {
                    return lessonDao.findOne(it);
                })
                .collect(Collectors.toList());

/*
       List<Lesson> lessons = teacherReq.getLessons().stream()
                .map(it -> lessonDao.findOne(it))
                .collect(Collectors.toList());

        List<Lesson> lessons2 = new ArrayList<>();
            for (String it : teacherReq.getLessons()) {
                 Lesson one = lessonDao.findOne(it);
                lessons2.add(one);
            }
  */
        teacher.setLessons(lessons);
        teacherDao.save(teacher);
    }

    @Override
    public void update(String id, Teacher request) {

    }
}
