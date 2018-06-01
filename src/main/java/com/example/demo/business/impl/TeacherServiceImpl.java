package com.example.demo.business.impl;

import com.example.demo.annotation.Action;
import com.example.demo.api.TeacherApi;
import com.example.demo.business.TeacherService;
import com.example.demo.dao.LessonDao;
import com.example.demo.dao.TeacherDao;
import com.example.demo.dto.TeacherDto;
import com.example.demo.entity.Lesson;
import com.example.demo.entity.Teacher;
import com.example.demo.request.TeacherReq;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by jiechen on 2018/1/14.
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TeacherServiceImpl.class);

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
    @Action("find2")
    public List<TeacherDto> find(TeacherReq request) {
        List<TeacherDto> teacherDtos = new ArrayList<>();
        List list=new ArrayList();
        for (Teacher teacher : teacherDao
                .findAll((root, query, cb) -> {
                    query.orderBy(cb.desc(root.get("id")));
                    List<Predicate> predicates = new LinkedList<>();
                    Optional.ofNullable(request.getid()).filter(it -> !it.isEmpty()).ifPresent(id -> {
                        predicates.add(cb.equal(root.get("id"), id));
                    });

                    Optional.ofNullable(request.getName()).filter(it -> !it.isEmpty()).ifPresent(name -> {
                        predicates.add(cb.equal(root.get("name"), name));
                    });
                    /*
                    String name = Optional.ofNullable(foo).map(Foo::getBat).map(Bat::getId).map(Id::getName).orElse("");
                    String s = null;
                    if (foo != null && foo.getBat() != null && foo.getBat.getId() != null) {
                        s = foo.getBat.getId()
                    }
                    */
                    Optional.ofNullable(request.getLessons()).ifPresent(lessonReq -> {
                        if (!lessonReq.isEmpty()) {
                            List<Lesson> lessons = lessonReq.stream()
                                    .map(it -> {
                                        return lessonDao.findOne(it);
                                    })
                                    .collect(Collectors.toList());

                            predicates.add(root.get("lessons").in(list));
                        }
                    });
                    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                })) {
            TeacherDto teacherDto = getTeacherDto(teacher);
            teacherDtos.add(teacherDto);
        }
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
