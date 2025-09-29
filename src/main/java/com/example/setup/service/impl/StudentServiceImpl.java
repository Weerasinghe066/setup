package com.example.setup.service.impl;

import com.example.setup.common.AppsErrorCode;
import com.example.setup.dao.StudentDAO;
import com.example.setup.exception.AppsException;
import com.example.setup.model.domain.Student;
import com.example.setup.model.dto.StudentDTO;
import com.example.setup.service.CourseService;
import com.example.setup.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);

    private StudentDAO studentDAO;

    private CourseService courseService;

    public StudentServiceImpl(StudentDAO studentDAO, CourseService courseService) {
        this.studentDAO = studentDAO;
        this.courseService = courseService;
    }

    //handle transactions

    public StudentDTO saveOrUpdateStudent(StudentDTO studentDTO) throws AppsException{

        LOG.info("START: Save or update Student: {}", studentDTO);

        Student student = null;
        boolean isNew = studentDTO.getStudentID() == null;

        if(isNew){
            student = new Student();
        }else{
            student = studentDAO.findById(studentDTO.getStudentID())
                    .orElseThrow(() -> new AppsException(AppsErrorCode.STUDENT_DOES_NOT_EXIST));
        }

        student.setStudentName(studentDTO.getStudentName());

        if(isNew){
            student.getCourses().addAll(this.courseService.findAllByIDs(studentDTO.getCourseIDs()));
        }else{
            if(!CollectionUtils.isEmpty(studentDTO.getDeletedCourseIDs())){
                student.removeCourses(this.courseService.findAllByIDs(studentDTO.getDeletedCourseIDs()));
            }

            if(!CollectionUtils.isEmpty(studentDTO.getAddedCourseIDs())){
                student.getCourses().addAll(this.courseService.findAllByIDs(studentDTO.getAddedCourseIDs()));
            }
        }

        student = studentDAO.saveAndFlush(student);

        LOG.info("END: Save or update Student: {}", studentDTO);
        return new StudentDTO(student);
    }


}
