package com.example.setup.model.dto;

import com.example.setup.model.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDTO {

    private Long studentID;

    private String studentName;

    private List<Long> courseIDs;

    private List<Long> deletedCourseIDs;

    private List<Long> addedCourseIDs;

    public StudentDTO(Student student) {
        this.studentID = student.getStudentID();
        this.studentName = student.getStudentName();
    }

    public StudentDTO() {
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<Long> getCourseIDs() {
        if (this.courseIDs == null) {
            courseIDs = new ArrayList<>();
        }
        return courseIDs;
    }

    public void setCourseIDs(List<Long> courseIDs) {
        this.courseIDs = courseIDs;
    }

    public List<Long> getDeletedCourseIDs() {
        if (deletedCourseIDs == null) {
            deletedCourseIDs = new ArrayList<>();
        }
        return deletedCourseIDs;
    }

    public void setDeletedCourseIDs(List<Long> deletedCourseIDs) {
        this.deletedCourseIDs = deletedCourseIDs;
    }

    public List<Long> getAddedCourseIDs() {
        if(addedCourseIDs == null){
            addedCourseIDs = new ArrayList<>();
        }
        return addedCourseIDs;
    }

    public void setAddedCourseIDs(List<Long> addedCourseIDs) {
        this.addedCourseIDs = addedCourseIDs;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "studentID=" + studentID +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
