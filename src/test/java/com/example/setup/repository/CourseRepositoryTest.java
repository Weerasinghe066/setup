package com.example.setup.repository;

import com.example.setup.dao.CourseDAO;
import com.example.setup.model.domain.Course;
import com.example.setup.model.domain.Subject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CourseRepositoryTest {


    @Autowired
    CourseDAO courseDAO;

    @Test
    public void CourseDao_SaveAll_ReturnSavedCourse() {

        Course course = new Course();
        course.setCourseName("Testing Course");

        Subject subject = new Subject();
        subject.setSubjectName("Maths");
        subject.setCourse(course);


        Subject subject2 = new Subject();
        subject2.setSubjectName("Maths");
        subject2.setCourse(course);

        course.addSubject(subject);
        course.addSubject(subject2);

        course = courseDAO.saveAndFlush(course);

        Assertions.assertThat(course).isNotNull();
        Assertions.assertThat(course.getId()).isGreaterThan(0);

    }

    @Test
    public void CourseDAO_FindAll_RetrieveAllSavedCourses() {

        Course course = new Course();
        course.setCourseName("Sudheera");

        Course course2 = new Course();
        course2.setCourseName("Sudheera");

        Course course3 = new Course();
        course3.setCourseName("Sudheera");


        courseDAO.saveAndFlush(course);
        courseDAO.saveAndFlush(course2);
        courseDAO.saveAndFlush(course3);

        Assertions.assertThat(courseDAO.findAll()).isNotNull();
        Assertions.assertThat(courseDAO.findAll().size()).isEqualTo(3);

    }

    //write to findByID method also
}
