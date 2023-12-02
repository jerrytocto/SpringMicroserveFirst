package com.microservice.course.controller;

import com.microservice.course.entity.Course;
import com.microservice.course.exeption.DatabaseOperationException;
import com.microservice.course.exeption.NumberFormatException;
import com.microservice.course.exeption.ResourceNotFoundException;
import com.microservice.course.service.CourseSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController{

    @Autowired
    private CourseSevice courseSevice ;

    @GetMapping
    public ResponseEntity<?> getAllCourses(){
        return ResponseEntity.ok(courseSevice.findAll());
    }

    @GetMapping("{idCourse}")
    public ResponseEntity<?> findById(@PathVariable Long idCourse){
        try{
            return ResponseEntity.ok(courseSevice.findById(idCourse));
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.ok(ex.getMessage());

        }catch (NumberFormatException ex){
            return ResponseEntity.ok(ex.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> findById(@RequestBody Course course){
        try{
            return ResponseEntity.ok(courseSevice.save(course));

        }catch (DatabaseOperationException ex){
            return ResponseEntity.ok(ex.getMessage());
        }
    }

    @GetMapping("/{courseId}/students")
    public ResponseEntity<?> studentsByCourseId(@PathVariable Long courseId){
        try{
            return ResponseEntity.ok(courseSevice.studentsByCourseId(courseId));
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
