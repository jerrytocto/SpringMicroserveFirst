package com.microservice.course.service.impl;

import com.microservice.course.dto.StudentDTO;
import com.microservice.course.entity.Course;
import com.microservice.course.exeption.DatabaseOperationException;
import com.microservice.course.exeption.NumberFormatException;
import com.microservice.course.exeption.ResourceNotFoundException;
import com.microservice.course.feignclient.StudentFeignClient;
import com.microservice.course.http.response.StudentByCourseResponse;
import com.microservice.course.repository.CourseRepository;
import com.microservice.course.service.CourseSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseSevice {

    @Autowired
    private CourseRepository courseRepository ;

    @Autowired
    private StudentFeignClient studentFeignClient;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long idCourse) {
        try{
            return courseRepository.findById(idCourse)
                    .orElseThrow(()-> new ResourceNotFoundException("Buscar curso","id",idCourse.intValue()));
        }catch (NumberFormatException ex){
            throw  new NumberFormatException("Buscar curso","Error al intentar buscar curso por id",ex);
        }
    }

    @Override
    public Course save(Course course) {
        try{
            return courseRepository.save(course);
        }catch (DatabaseOperationException ex) {
            throw new DatabaseOperationException("Guardar Curso", "Error al intentar guardar un curso", ex);
        }
    }

    @Override
    public StudentByCourseResponse studentsByCourseId(Long idCourse) {
        Course courseFound = courseRepository.findById(idCourse)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + idCourse, "id", idCourse.intValue()));
        //Se recupera la lista de estudiantes desde el microservicio student haciendo uso de nuestra interfaz
        //configurada en el package feignClient

        List<StudentDTO> studentDTOList = studentFeignClient.listStudentByCourse(idCourse);

        return StudentByCourseResponse.builder()
                .courseName(courseFound.getName())
                .teacher(courseFound.getTeacher())
                .studentDTOList(studentDTOList)
                .build();
    }
}
