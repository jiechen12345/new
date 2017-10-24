package com.example.demo.api;

import com.example.demo.dao.LessonDao;
import com.example.demo.entity.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jiechen on 2017/10/7.
 */
@RestController
@RequestMapping("/lessons")
public class LessonApi {
    @Autowired
    private LessonDao lessonDao;

    @RequestMapping(method = RequestMethod.GET)
    public List<Lesson> findAll() {
        return lessonDao.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void creat(@RequestBody Lesson lesson) {
        lessonDao.save(lesson);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable String id, @RequestBody Lesson request) {
        Lesson lesson = lessonDao.findOne(id);
        lesson.setName(request.getName());
        lesson.setTeachers(request.getTeachers());
        lessonDao.save(lesson);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        Lesson lesson = lessonDao.findOne(id);
        lessonDao.delete(lesson);
    }

}
