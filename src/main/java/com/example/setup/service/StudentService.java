package com.example.setup.service;

import com.example.setup.exception.AppsException;
import com.example.setup.model.dto.StudentDTO;

public interface StudentService {

    StudentDTO saveOrUpdateStudent(StudentDTO studentDTO) throws AppsException;
}
