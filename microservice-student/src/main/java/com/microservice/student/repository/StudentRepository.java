package com.microservice.student.repository;

import com.microservice.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.courseId= :idCourse")
    List<Student> listStudentsByCourseId(Long idCourse);

    @Query(value = "SELECT * FROM tb_students WHERE id = :idCourse", nativeQuery = true)
    List<Student> studentsByCourseId(@Param("idCourse") Long idCourse);
}
