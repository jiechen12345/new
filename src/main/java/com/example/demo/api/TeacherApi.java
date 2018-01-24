package com.example.demo.api;

import com.example.demo.business.TeacherService;
import com.example.demo.dao.TeacherDao;
import com.example.demo.dto.TeacherDto;
import com.example.demo.entity.Lesson;
import com.example.demo.entity.Teacher;
import com.example.demo.request.TeacherReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jiechen on 2017/10/7.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value ="/teachers",produces = "application/json")
public class TeacherApi {
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(method = RequestMethod.GET)
    public List<TeacherDto> findAll() {
       return teacherService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<TeacherDto> creat(@RequestBody TeacherReq teacher) {
        System.out.println(teacher.toString());
        teacherService.creat(teacher);
        return teacherService.findAll();
    }

//    @RequestMapping(value = "/{id}/lesson", met
// hod = RequestMethod.POST)
//    public void creat(@PathVariable String id, @RequestBody List<Lesson> lessonList) {
//        Teacher teacher = teacherDao.findOne(id);
//        for (Lesson lesson : lessonList) {
//            teacher.getLessons().add(lesson);
//        }
//        teacherDao.save(teacher);
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public List<TeacherDto> update(@PathVariable String id, @RequestBody TeacherReq teacherReq) {
        teacherService.update(id,teacherReq);
        return teacherService.findAll();
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
    public List<TeacherDto> delete(@PathVariable String id) {
        //Teacher teacher = teacherDao.findOne(id);
        //teacherDao.delete(teacher);
        teacherService.delete(id);
        return teacherService.findAll();
    }
}
