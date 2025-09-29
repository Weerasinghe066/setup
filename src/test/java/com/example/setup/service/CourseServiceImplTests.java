package com.example.setup.service;

import com.example.setup.dao.CourseDAO;
import com.example.setup.exception.AppsException;
import com.example.setup.model.domain.Course;
import com.example.setup.model.domain.Subject;
import com.example.setup.model.dto.CourseDTO;
import com.example.setup.model.dto.SubjectDTO;
import com.example.setup.service.impl.CourseServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceImplTests {

    @Mock
    private CourseDAO courseDAO;

    @Mock
    private SubjectService subjectService;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    public void CourseService_Test_SaveCourse() throws AppsException {

        Course course = new Course();
        course.setCourseName("Testing Course");

        Subject subject = new Subject();
        subject.setSubjectName("Maths");

        Subject subject2 = new Subject();
        subject2.setSubjectName("Maths");

        course.addSubject(subject);
        course.addSubject(subject2);


        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseName("Testing Course");

        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setSubjectName("Maths");

        SubjectDTO subjectDTO2 = new SubjectDTO();
        subjectDTO2.setSubjectName("Maths");

        courseDTO.getSubjects().add(subjectDTO);
        courseDTO.getSubjects().add(subjectDTO2);

        when(courseDAO.saveAndFlush(Mockito.any(Course.class))).thenReturn(course);

        CourseDTO savedCourse = courseService.saveOrUpdateCourse(courseDTO);

        Assertions.assertThat(savedCourse).isNotNull();


    }

    @Test
    public void CourseService_Test_UpdateCourse() throws AppsException {

        Course course = new Course();
        course.setId(1L);
        course.setCourseName("Testing Course");

        Subject subject = new Subject();
        subject.setSubjectID(1L);
        subject.setSubjectName("Maths");

        Subject subject2 = new Subject();
        subject.setSubjectID(2L);
        subject2.setSubjectName("Maths");

        course.addSubject(subject);
        course.addSubject(subject2);


        CourseDTO courseDTO = new CourseDTO();
        course.setId(1L);
        courseDTO.setCourseName("Testing Course");

        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setSubjectID(1L);
        subjectDTO.setCourseID(1L);
        subjectDTO.setSubjectName("Maths");

        SubjectDTO subjectDTO2 = new SubjectDTO();
        subjectDTO2.setSubjectID(2L);
        subjectDTO2.setCourseID(1L);
        subjectDTO2.setSubjectName("Maths");

        courseDTO.getSubjects().add(subjectDTO);
        courseDTO.getSubjects().add(subjectDTO2);

        when(subjectService.findSubjectByID(1L)).thenReturn(Optional.ofNullable(subject));
        when(subjectService.findSubjectByID(2L)).thenReturn(Optional.ofNullable(subject2));
        when(courseDAO.saveAndFlush(Mockito.any(Course.class))).thenReturn(course);

        CourseDTO savedCourse = courseService.saveOrUpdateCourse(courseDTO);

        Assertions.assertThat(savedCourse).isNotNull();
    }

}
