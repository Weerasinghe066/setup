package com.example.setup.service;

import com.example.setup.model.domain.Subject;

import java.util.Optional;

public interface SubjectService {

    Optional<Subject> findSubjectByID(Long id);
}
