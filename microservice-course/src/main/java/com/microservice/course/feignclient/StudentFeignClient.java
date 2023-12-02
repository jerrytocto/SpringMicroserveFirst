package com.microservice.course.feignclient;

import com.microservice.course.dto.StudentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//En esta interfaz se creará el cliente que permitirá la conexión entre nuestros clientes

//La propiedada name es igual al nombre del microservicio del que se consumirá la información
@FeignClient(name = "mcsv-student", url = "localhost:8091/api/v1/students")
public interface StudentFeignClient {

    @GetMapping("/course/{idCourse}")
    List<StudentDTO> listStudentByCourse(@PathVariable Long idCourse);


}
