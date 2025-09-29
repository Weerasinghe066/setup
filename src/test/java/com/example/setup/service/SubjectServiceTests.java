package com.example.setup.service;

import com.example.setup.dao.SubjectDAO;
import com.example.setup.model.domain.Subject;
import com.example.setup.service.impl.SubjectServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceTests {

    @Mock
    private SubjectDAO subjectDAO;

    @InjectMocks
    private SubjectServiceImpl subjectService;

    @Test
    public void SubjectService_FindSubjectByID_ReturnSubject(){

        Subject subject = new Subject();
        subject.setSubjectID(1L);
        subject.setSubjectName("Maths");

        when(subjectDAO.findById((1L))).thenReturn(Optional.ofNullable(subject));
        Assertions.assertThat(subjectService.findSubjectByID(1L)).isNotNull();

    }
}
