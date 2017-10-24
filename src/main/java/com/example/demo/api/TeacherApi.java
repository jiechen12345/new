package com.example.demo.api;

import com.example.demo.dao.TeacherDao;
import com.example.demo.entity.Lesson;
import com.example.demo.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jiechen on 2017/10/7.
 */
@RestController
@RequestMapping("/teachers")
public class TeacherApi {
    @Autowired
    private TeacherDao teacherDao;

    @RequestMapping(method = RequestMethod.GET)
    public List<Teacher> findAll() {
        return teacherDao.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void creat(@RequestBody Teacher teacher) {
        teacherDao.save(teacher);
    }

    @RequestMapping(value = "/{id}/lesson", method = RequestMethod.POST)
    public void creat(@PathVariable String id, @RequestBody List<Lesson> lessonList) {
        Teacher teacher = teacherDao.findOne(id);
        for (Lesson lesson : lessonList) {
            teacher.getLessons().add(lesson);
        }
        teacherDao.save(teacher);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable String id, @RequestBody Teacher request) {
        Teacher teacher = teacherDao.findOne(id);
        teacher.setName(request.getName());
        teacher.setLessons(request.getLessons());
        teacherDao.save(teacher);
    }

    @RequestMapping(value = "/{id}/lesson", method = RequestMethod.PUT)
    public void update(@PathVariable String id, @RequestBody List<Lesson> lessonList) {
        Teacher teacher = teacherDao.findOne(id);
        teacher.getLessons().clear();
        teacher.getLessons().addAll(lessonList);
        // 單純增加
//        List newTempList=new ArrayList<Lesson>();
//        List oldTempList=new ArrayList<Lesson>() ;
//        if(!lessonList.containsAll(oldLessons)) {
//            newTempList.addAll(lessonList);
//            oldTempList.addAll(oldLessons);
//            newTempList.removeAll(oldTempList);
//            teacher.getLessons().addAll(newTempList);
//            if(!lessonList.containsAll(oldTempList)) {
//            }
//    }
        teacherDao.save(teacher);
}

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        Teacher teacher = teacherDao.findOne(id);
        teacherDao.delete(teacher);
    }
}
