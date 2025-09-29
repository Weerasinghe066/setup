package com.example.setup.service;

import com.example.setup.exception.AppsException;
import com.example.setup.model.domain.Course;
import com.example.setup.model.dto.CourseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {

    CourseDTO saveOrUpdateCourse(CourseDTO courseDTO) throws AppsException;

    List<Course> findAllByIDs(List<Long> courseIDs) throws AppsException;

    Page<CourseDTO> findAll(Integer page, Integer size);

}
