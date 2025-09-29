package com.example.setup.controller;

import com.example.setup.model.dto.StudentDTO;
import com.example.setup.service.impl.StudentServiceImpl;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "home")
public class HomeController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    private StudentServiceImpl studentServiceImpl;

    public HomeController(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

    @RequestMapping(value = "/test", headers = "Accept=application/json", method = RequestMethod.GET)
    public ResponseEntity<String> sayHello() {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }


    @RequestMapping(value = "/saveOrUpdateStudent", headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<StudentDTO> saveOrUpdateStudent(@RequestBody StudentDTO studentDTO) throws Exception {
        LOG.info("START: Saving student {}", studentDTO);
        StudentDTO student = this.studentServiceImpl.saveOrUpdateStudent(studentDTO);
        LOG.info("END: Saving student {}", studentDTO);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

}
