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

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Override
    public List<TeacherDto> find(TeacherReq request) {
        List<TeacherDto> teacherDtos = teacherDao
                .findAll((root, query, cb) -> {
                    query.orderBy(cb.desc(root.get("id")));
                    List<Predicate> predicates = new LinkedList<>();
                    Optional.ofNullable(request.getid()).ifPresent(id -> {
                        predicates.add(cb.equal(root.get("id"), id));
                    });
                    Optional.ofNullable(request.getName()).ifPresent(name -> {
                        predicates.add(cb.equal(root.get("name"), name));
                    });
                    Optional.ofNullable(request.getLessons()).ifPresent(lessons -> {
                        if (!lessons.isEmpty()) {
                            predicates.add(root.get("lessons").in(lessons));
                        }
                    });
                    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                })
                .stream()
                .map(this::getTeacherDto)
                .collect(Collectors.toList());
        return teacherDtos;
    }

    private TeacherDto getTeacherDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setName(teacher.getName());
        teacherDto.setId(teacher.getId());
        teacherDto.setLessons(teacher.getLessons().stream().map(it -> it.getName()).collect(toList()));
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
        findAll();
    }

    @Override
    public void update(String id, TeacherReq teacherReq) {
        Teacher teacher = teacherDao.findOne(id);
        teacher.setName(teacherReq.getName());
        teacher.setLessons( teacherReq.getLessons().stream().map(it -> lessonDao.findOne(it)).collect(toList()));
        teacherDao.save(teacher);
    }
    @Override
    public void delete(String id) {
        Teacher teacher = teacherDao.findOne(id);
        teacherDao.delete(teacher);
    }
}
