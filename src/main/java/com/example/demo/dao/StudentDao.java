package com.example.demo.dao;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiechen on 2017/10/7.
 */
@Repository
public interface StudentDao extends JpaRepository<Student,String> {

}
