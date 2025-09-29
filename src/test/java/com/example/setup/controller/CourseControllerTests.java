package com.example.setup.controller;

import com.example.setup.exception.AppsException;
import com.example.setup.model.dto.CourseDTO;
import com.example.setup.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(controllers = CourseController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CourseControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CourseService courseService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void CourseController_SaveOrUpdate_ReturnCourse() throws Exception {

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseName("Testing Course");

        when(courseService.saveOrUpdateCourse(Mockito.any())).thenReturn(courseDTO);

//        given(courseService.saveOrUpdateCourse(ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/course/saveOrUpdateCourse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(courseDTO)));

        response.andExpect(MockMvcResultMatchers.status().is(200));

    }

}
