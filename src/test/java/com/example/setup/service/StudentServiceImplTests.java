package com.example.setup.service;

import com.example.setup.dao.StudentDAO;
import com.example.setup.exception.AppsException;
import com.example.setup.model.domain.Course;
import com.example.setup.model.domain.Student;
import com.example.setup.model.dto.StudentDTO;
import com.example.setup.service.impl.StudentServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTests {

    @Mock
    StudentDAO studentDAO;

    @Mock
    CourseService courseService;

    @InjectMocks
    StudentServiceImpl studentService;


    @Test
    public void StudentService_CreateNewStudent_ReturnStudent() throws AppsException {

        Course course = new Course();
        course.setId(1L);
        course.setCourseName("Testing Course");

        Course course1 = new Course();
        course1.setId(2L);
        course1.setCourseName("Testin Course");

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentName("thilina");
        studentDTO.getAddedCourseIDs().add(1L);
        studentDTO.getAddedCourseIDs().add(2L);

        Student student = new Student();
        student.setStudentID(1L);
        student.setStudentName("thilina");
        student.setCourses(Set.of(course, course1));

        when(courseService.findAllByIDs(studentDTO.getCourseIDs())).thenReturn(List.of(course, course1));
        when(studentDAO.saveAndFlush(Mockito.any(Student.class))).thenReturn(student);

        StudentDTO savedStudent = studentService.saveOrUpdateStudent(studentDTO);

        Assertions.assertThat(savedStudent).isNotNull();

    }

    @Test
    public void StudentService_UpdateCurrentStudent_ReturnStudent() throws AppsException {

        Course course = new Course();
        course.setId(1L);
        course.setCourseName("Testing first Course");

        Course course1 = new Course();
        course1.setId(2L);
        course1.setCourseName("Testing second Course");

        Course course3 = new Course();
        course3.setId(3L);
        course3.setCourseName("Testing third Course");

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentID(1L);
        studentDTO.setStudentName("thilina");
        studentDTO.getDeletedCourseIDs().add(1L);
        studentDTO.getAddedCourseIDs().add(3L);

        Student student = new Student();
        student.setStudentID(1L);
        student.setStudentName("thilina");
        student.setCourses(new HashSet<>(Arrays.asList(course1, course)));

        Student updatedStudent = new Student();
        student.setStudentID(1L);
        student.setStudentName("updated thilina");
        student.setCourses(new HashSet<>(Arrays.asList(course1, course3)));

        when(studentDAO.findById(1L)).thenReturn(Optional.of(student));
        when(courseService.findAllByIDs(Arrays.asList(1L))).thenReturn(List.of(course));
        when(courseService.findAllByIDs(Arrays.asList(3L))).thenReturn(List.of(course3));
        when(studentDAO.saveAndFlush(Mockito.any(Student.class))).thenReturn(updatedStudent);

        StudentDTO savedStudent = studentService.saveOrUpdateStudent(studentDTO);

        Assertions.assertThat(savedStudent).isNotNull();

    }
}
