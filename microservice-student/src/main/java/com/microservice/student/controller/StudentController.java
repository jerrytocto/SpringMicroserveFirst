package com.microservice.student.controller;

import com.microservice.student.entity.Student;
import com.microservice.student.exeption.DatabaseOperationException;
import com.microservice.student.exeption.ResourceNotFoundException;
import com.microservice.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping()
    public ResponseEntity<?> getAllStudent(){
        return ResponseEntity.ok().body(studentService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?>findStudentById(@PathVariable Long id){
        try{
            return ResponseEntity.ok().body(studentService.findStudentById(id));
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.ok().body(ex.getMessage());

        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveStusent(@RequestBody Student student){
        try{
            return  ResponseEntity.ok(studentService.saveStudent(student));
        }catch (DatabaseOperationException ex){
            return ResponseEntity.ok(ex.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@RequestBody Student student, @PathVariable Long idStudent){
        try{
            return  ResponseEntity.ok(studentService.updateStudent(student, idStudent));

        }catch (ResourceNotFoundException ex){
            return ResponseEntity.ok(ex.getMessage());

        } catch (DatabaseOperationException ex){
            return ResponseEntity.ok(ex.getMessage());
        }
    }

    @GetMapping("/course/{idCourse}")
    public ResponseEntity<?> studentsByCourseId(@PathVariable Long idCourse){
        return ResponseEntity.ok().body(studentService.studentByCourseId(idCourse));
    }
}
