package com.example.demo.api;

import com.example.demo.annotation.Action;
import com.example.demo.business.TeacherService;
import com.example.demo.dao.TeacherDao;
import com.example.demo.dto.TeacherDto;
import com.example.demo.entity.Lesson;
import com.example.demo.entity.Teacher;
import com.example.demo.request.TeacherReq;
import javafx.application.Application;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jiechen on 2017/10/7.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value ="/teachers",produces = "application/json")
public class TeacherApi {
    //private static final Logger LOGGER = LoggerFactory.getLogger(TeacherApi.class);
    Logger LOGGER = LoggerFactory.getLogger(TeacherApi.class);

    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/success" )
    public String success(Map<String,Object> map) {
        LOGGER.debug("123");
        LOGGER.info("123");
        LOGGER.warn("789");
        map.put("aa","aa");
        return "success";
    }

    @RequestMapping(method = RequestMethod.GET)
    @Action("findAll")
    public List<TeacherDto> findAll() {
        LOGGER.debug("123");
        LOGGER.info("456");
        LOGGER.warn("789");
        LOGGER.error("10");
       return teacherService.findAll();
    }

    @RequestMapping(value = "/search" ,method = RequestMethod.POST)
    public List<TeacherDto> find(@RequestBody TeacherReq q_teacher) {
        System.out.println(q_teacher.toString());
        return teacherService.find(q_teacher);
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
