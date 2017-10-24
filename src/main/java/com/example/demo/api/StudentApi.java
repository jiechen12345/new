package com.example.demo.api;

import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Lesson;
import com.example.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jiechen on 2017/10/7.
 */
@RestController
@RequestMapping("/students")
public class StudentApi {
    @Autowired
    private StudentDao studentDao;

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void creat(@RequestBody Student student) {
        studentDao.save(student);
    }

    @RequestMapping(value = "/{id}/lesson", method = RequestMethod.POST)
    public void creat(@PathVariable String id, @RequestBody List<Lesson> lessonList) {
        Student student = studentDao.findOne(id);
        for (Lesson lesson : lessonList) {
            student.getLessons().add(lesson);
        }
        studentDao.save(student);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable String id, @RequestBody Student request) {
        Student student = studentDao.findOne(id);
        student.setName(request.getName());
        student.setLessons(request.getLessons());
        studentDao.save(student);
    }

    @RequestMapping(value = "/{id}/lesson", method = RequestMethod.PUT)
    public void update(@PathVariable String id, @RequestBody List<Lesson> lessonList) {
        Student student = studentDao.findOne(id);
        student.getLessons().clear();
        student.getLessons().addAll(lessonList);
        // 單純增加
//        List newTempList=new ArrayList<Lesson>();
//        List oldTempList=new ArrayList<Lesson>() ;
//        if(!lessonList.containsAll(oldLessons)) {
//            newTempList.addAll(lessonList);
//            oldTempList.addAll(oldLessons);
//            newTempList.removeAll(oldTempList);
//            student.getLessons().addAll(newTempList);
//            if(!lessonList.containsAll(oldTempList)) {
//            }
//    }
        studentDao.save(student);
}

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        Student student = studentDao.findOne(id);
        studentDao.delete(student);
    }
}
