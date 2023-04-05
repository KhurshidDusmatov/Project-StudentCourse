package com.company.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "student_t")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "age")
    private Integer age;
    @Column(name = "psw")
    private String psw;
    @Column(name = "phone", unique = true)
    private String phone;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "visible")
    private Boolean visible;
}
