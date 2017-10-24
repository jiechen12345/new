package com.example.demo.dao;

import com.example.demo.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiechen on 2017/10/7.
 */
@Repository
public interface LessonDao extends JpaRepository<Lesson,String> {
}
