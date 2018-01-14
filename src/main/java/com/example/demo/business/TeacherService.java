package com.example.demo.business;

import com.example.demo.entity.Teacher;
import com.example.demo.request.TeacherReq;

/**
 * Created by jiechen on 2018/1/14.
 */
public interface TeacherService {
    void creat(TeacherReq teacher);
    void update(String id,Teacher request);
}
