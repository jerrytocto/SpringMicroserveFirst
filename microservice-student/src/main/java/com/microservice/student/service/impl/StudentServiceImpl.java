package com.microservice.student.service.impl;

import com.microservice.student.entity.Student;
import com.microservice.student.exeption.DatabaseOperationException;
import com.microservice.student.exeption.NumberFormatException;
import com.microservice.student.exeption.ResourceNotFoundException;
import com.microservice.student.repository.StudentRepository;
import com.microservice.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Buscar estudiante","id", id.intValue()));
    }

    @Override
    public Student saveStudent(Student student) {
        try{
            return studentRepository.save(student);
        }catch (DatabaseOperationException ex){
            throw new DatabaseOperationException("Guardar un studiante","Error al registrar un studiante a la base de datos",ex);
        }

    }

    @Override
    public Student updateStudent(Student student, Long idStudent) {

        try{
            Student studentFound= studentRepository.findById(idStudent).
                    orElseThrow(()->new ResourceNotFoundException("Actualizar estudiante","id",idStudent.intValue()));

            studentFound.setCourseId(student.getCourseId());
            studentFound.setName(student.getName());
            studentFound.setEmail(student.getEmail());
            studentFound.setLastName(student.getLastName());

            return studentRepository.save(studentFound);

        }catch (NumberFormatException ex){
            throw new NumberFormatException("Actulizar student","Error al actualizar el usuario con id:"+idStudent,ex);

        }

    }


    @Override
    public List<Student> studentByCourseId(Long idCourse) {

        //Está pendiente la verificación si esque el código existe
        List<Student> listStudentsCourseId = studentRepository.listStudentsByCourseId(idCourse);
        return listStudentsCourseId;
    }
}
