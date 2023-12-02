package com.microservice.student.service;

import com.microservice.student.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAll();

    Student findStudentById(Long id);

    Student saveStudent(Student student);

    Student updateStudent(Student student, Long idStudent);

    List<Student> studentByCourseId(Long idCourse);
}
