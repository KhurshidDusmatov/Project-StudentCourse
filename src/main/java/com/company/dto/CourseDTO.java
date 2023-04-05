package com.company.dto;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class CourseDTO {
    private Integer id;
    private String name;
    private Double price;
    private String duration;
    private LocalDateTime createdDate;
}
