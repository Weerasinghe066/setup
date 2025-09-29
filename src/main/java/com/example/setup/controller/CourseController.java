package com.example.setup.controller;

import com.example.setup.exception.AppsException;
import com.example.setup.model.dto.CourseDTO;
import com.example.setup.service.CourseService;
import com.example.setup.service.impl.CourseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "course")
public class CourseController {

    private static final Logger LOG = LoggerFactory.getLogger(CourseController.class);


    private CourseService courseService;


    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(value = "/saveOrUpdateCourse", method = RequestMethod.POST)
    public ResponseEntity<CourseDTO> saveOrUpdateCourse(@RequestBody CourseDTO courseDTO) throws AppsException {
        LOG.info("START: Save or Update Course {}", courseDTO);
        CourseDTO course = this.courseService.saveOrUpdateCourse(courseDTO);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @RequestMapping(value = "/getPagedCourses", method = RequestMethod.GET)
    public ResponseEntity<Page<CourseDTO>> getPagedCourses(){
        //Create a DTO that get the request param as page and pagesize
        LOG.info("START: Get paged courses");
        Page<CourseDTO> pagedCourse = this.courseService.findAll(0, 5);
        LOG.info("END: Get paged courses");
        return new ResponseEntity<>(pagedCourse, HttpStatus.OK);
    }


}
