package com.company.controller;

import com.company.dto.BetweenDateDTO;
import com.company.dto.StudentDTO;
import com.company.entity.StudentEntity;
import com.company.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public ResponseEntity<List<StudentDTO>> getAll() {
        List<StudentDTO> list = studentService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        StudentDTO dto = studentService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentDTO studentDTO) {
        StudentDTO response = studentService.create(studentDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.update(id, studentDTO));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.delete(id));
    }

//    @GetMapping(value = "get-by-some-field/{someField}")
//    public ResponseEntity<?> getBySomeField(@PathVariable("someField") String someField){
//        List<StudentDTO> list = studentService.getBySomeField(someField);
//        return ResponseEntity.ok(list);
//    }

    @GetMapping(value = "list-by-given-date/{date}")
    public ResponseEntity<List<StudentDTO>> getListByGivenDate(@PathVariable("date") LocalDateTime date){
        List<StudentDTO> list = studentService.getListByGivenDate(date);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "list-by-given-dates")
    public ResponseEntity<List<StudentDTO>> getListByGivenDate(@RequestBody BetweenDateDTO dto){
        List<StudentDTO> list = studentService.getListByGivenDates(dto);
        return ResponseEntity.ok(list);
    }



}
