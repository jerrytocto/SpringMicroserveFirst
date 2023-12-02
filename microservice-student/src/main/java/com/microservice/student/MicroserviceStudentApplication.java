package com.microservice.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


					// servicio(Es decir quien permitirá la comunicación de nuestros microservicios)
@EnableDiscoveryClient // estamos estableciendo que este microservicio sea un cliente de eureka
@SpringBootApplication
public class MicroserviceStudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceStudentApplication.class, args);
	}

}
