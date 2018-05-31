package com.example.demo.dao;

import com.example.demo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by jiechen on 2017/10/7.
 */
@Repository
public interface TeacherDao extends JpaRepository<Teacher,String>, JpaSpecificationExecutor<Teacher> {
}
