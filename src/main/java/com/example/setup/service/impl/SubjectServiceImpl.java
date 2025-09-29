package com.example.setup.service.impl;

import com.example.setup.dao.SubjectDAO;
import com.example.setup.model.domain.Subject;
import com.example.setup.service.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectDAO subjectDAO;

    public SubjectServiceImpl(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Optional<Subject> findSubjectByID(Long id) {
        return this.subjectDAO.findById(id);
    }
}
