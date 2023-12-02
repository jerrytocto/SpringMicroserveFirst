package com.microservice.course.dto;

import jakarta.persistence.Column;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private String name ;
    private String lastName ;
    private String email;
    private Long courseId;

}
