package com.company.controller;

import com.company.dto.BetweenDateDTO;
import com.company.dto.BetweenPriceDTO;
import com.company.dto.CourseDTO;
import com.company.dto.StudentDTO;
import com.company.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody CourseDTO dto) {
        CourseDTO response = courseService.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CourseDTO>> getAll() {
        List<CourseDTO> list = courseService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        CourseDTO dto = courseService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody CourseDTO dto) {
        return ResponseEntity.ok(courseService.update(id, dto));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(courseService.delete(id));
    }

    @GetMapping(value = "list-by-between-prices")
    public ResponseEntity<List<CourseDTO>> getListByPrices(@RequestBody BetweenPriceDTO dto){
        List<CourseDTO> list = courseService.getListByPrices(dto);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "list-by-given-dates")
    public ResponseEntity<List<CourseDTO>> getListByGivenDate(@RequestBody BetweenDateDTO dto){
        List<CourseDTO> list = courseService.getListByGivenDates(dto);
        return ResponseEntity.ok(list);
    }



}
