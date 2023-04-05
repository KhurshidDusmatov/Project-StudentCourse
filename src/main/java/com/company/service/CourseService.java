package com.company.service;

import com.company.dto.BetweenDateDTO;
import com.company.dto.BetweenPriceDTO;
import com.company.dto.CourseDTO;
import com.company.dto.StudentDTO;
import com.company.entity.CourseEntity;
import com.company.entity.StudentEntity;
import com.company.exp.AppBadRequestException;
import com.company.exp.CourseNotFoundException;
import com.company.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public CourseDTO create(CourseDTO dto) {
        CourseEntity entity = new CourseEntity();
        entity.setName(dto.getName());
        entity.setDuration(dto.getDuration());
        entity.setPrice(dto.getPrice());
        entity.setCreatedDate(LocalDate.now());
        check(dto);
        courseRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public List<CourseDTO> getAll() {
        Iterable<CourseEntity> iterable = courseRepository.findAll();
        List<CourseDTO> dtoList = new LinkedList<>();

        iterable.forEach(entity -> {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        });
        return dtoList;
    }

    public CourseDTO getById(Integer id) {
        CourseEntity entity = get(id);
        CourseDTO dto = new CourseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDuration(entity.getDuration());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }


    private CourseEntity get(Integer id){
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if(optional.isEmpty()){
            throw new CourseNotFoundException("No course with this ID was found : " + id);
        }
       return optional.get();
    }



    public boolean update(Integer id, CourseDTO dto) {
        CourseEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setDuration(dto.getDuration());
        entity.setPrice(dto.getPrice());
//        entity.setCreatedDate(LocalDateTime.now());
        check(dto);
        courseRepository.save(entity);
        return true;
    }

    public boolean delete(Integer id) {
        CourseEntity entity = get(id);
        courseRepository.delete(entity);
        return true;
    }

    public List<CourseDTO> getListByPrices(BetweenPriceDTO dto) {
        List<CourseEntity> entities = courseRepository.findAllByPriceBetween(dto.getFromPrice(), dto.getUntilPrice());
        return parseToDto(entities);
    }

    public List<CourseDTO> parseToDto(List<CourseEntity> entities){
        List<CourseDTO> dtos = new LinkedList<>();
        entities.forEach(entity -> {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            dtos.add(dto);
        });
        return dtos;
    }

    private void check(CourseDTO dto){
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new AppBadRequestException("Name is invalid");
        }
        if (dto.getDuration() == null || dto.getDuration().isBlank()) {
            throw new AppBadRequestException("Duration is invalid");
        }
        if (dto.getPrice() == null || dto.getPrice() < 0) {
            throw new AppBadRequestException("Price is invalid");
        }
    }

    public List<CourseDTO> getListByGivenDates(BetweenDateDTO dto) {
        List<CourseEntity> entities = courseRepository.findAllByCreatedDateBetween(dto.getFromDate(), dto.getToDate());
        return parseToDto(entities);
    }
}
