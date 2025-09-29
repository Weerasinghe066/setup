package com.example.setup.dao;

import com.example.setup.model.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDAO extends JpaRepository<Course, Long> {
}
