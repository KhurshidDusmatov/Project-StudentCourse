package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class CourseDTO {
    private Integer id;
    private String name;
    private Double price;
    private String duration;
    private LocalDate createdDate;
}
