package com.example.setup.model.dto;

import com.example.setup.model.domain.Course;
import com.example.setup.model.domain.Subject;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseDTO implements Serializable {

    private Long courseID;

    private String courseName;

    private List<SubjectDTO> subjects;

    public CourseDTO(Course course) {
        this.courseID = course.getId();
        this.courseName = course.getCourseName();
        if(!CollectionUtils.isEmpty(course.getSubjects())){
            for(Subject subject : course.getSubjects()){
                this.getSubjects().add(new SubjectDTO(subject));
            }
        }
    }

    public CourseDTO() {
    }

    public Long getCourseID() {
        return courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<SubjectDTO> getSubjects() {
        if(this.subjects == null){
            subjects = new ArrayList<>();
        }
        return subjects;
    }

    public void setSubjects(List<SubjectDTO> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "courseID=" + courseID +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
