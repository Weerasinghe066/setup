package com.example.setup.model.dto;

import com.example.setup.model.domain.Subject;

import java.io.Serializable;

public class SubjectDTO implements Serializable {

    private Long subjectID;

    private Long courseID;

    private String subjectName;

    public SubjectDTO(Subject subject) {
        this.subjectID = subject.getSubjectID();
        this.courseID = subject.getCourse().getId();
        this.subjectName = subject.getSubjectName();
    }

    public SubjectDTO() {
    }

    public Long getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Long subjectID) {
        this.subjectID = subjectID;
    }

    public Long getCourseID() {
        return courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "SubjectDTO{" +
                "subjectID=" + subjectID +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }
}
