package com.microservice.course.service;

import com.microservice.course.entity.Course;
import com.microservice.course.http.response.StudentByCourseResponse;

import java.util.List;

public interface CourseSevice {

    List<Course> findAll();

    Course findById(Long idCourse);

    Course save(Course course);
    StudentByCourseResponse studentsByCourseId(Long idCourse);

}
