package com.example.setup.service.impl;

import com.example.setup.common.AppsErrorCode;
import com.example.setup.dao.CourseDAO;
import com.example.setup.exception.AppsException;
import com.example.setup.model.domain.Course;
import com.example.setup.model.domain.Subject;
import com.example.setup.model.dto.CourseDTO;
import com.example.setup.model.dto.SubjectDTO;
import com.example.setup.service.CourseService;
import com.example.setup.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger LOG = LoggerFactory.getLogger(CourseServiceImpl.class);

    private CourseDAO courseDAO;

    private SubjectService subjectService;

    public CourseServiceImpl(CourseDAO courseDAO, SubjectService subjectService) {
        this.courseDAO = courseDAO;
        this.subjectService = subjectService;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = AppsException.class)
    public CourseDTO saveOrUpdateCourse(CourseDTO courseDTO) throws AppsException {

        LOG.info("START: Save or Update Course: {}", courseDTO);
        Course course = null;

        if (courseDTO.getCourseID() == null) {
            course = new Course();
        }else{
            course = this.courseDAO.findById(courseDTO.getCourseID())
                    .orElseThrow(()-> new AppsException(AppsErrorCode.COURSE_DOES_NOT_EXIST));
        }

        course.setCourseName(courseDTO.getCourseName());

        if(!CollectionUtils.isEmpty(courseDTO.getSubjects())){
            for(SubjectDTO subjectDTO : courseDTO.getSubjects()){
                boolean isNew = subjectDTO.getSubjectID() == null;
                Subject subject = null;
                if(isNew){
                    subject = new Subject();
                    course.addSubject(subject);
                }else{
                    subject = this.subjectService.findSubjectByID(subjectDTO.getSubjectID())
                            .orElseThrow(() -> new AppsException(AppsErrorCode.SUBJECT_DOES_NOT_EXIST));
                }
                subject.setSubjectName(subjectDTO.getSubjectName());
                subject.setCourse(course);
            }
        }

        course = this.courseDAO.saveAndFlush(course);
        LOG.info("END: Save or Update Course: {}", courseDTO);
        return new CourseDTO(course);
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Course> findAllByIDs(List<Long> ids){
        return this.courseDAO.findAllById(ids);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Page<CourseDTO> findAll(Integer page, Integer size){
        LOG.info("START: Get paged courses");
        PageRequest pageRequest = PageRequest.of(page, size);
        LOG.info("END: Get paged courses");
        return this.courseDAO.findAll(pageRequest).map((CourseDTO::new));
    }

//    private Subject dtoToEntity(SubjectDTO subjectDTO) throws AppsException{
//        boolean isNew = subjectDTO.getSubjectID() == null;
//        Subject subject = null;
//        if(isNew){
//            subject = new Subject();
//        }else{
//            subject = this.subjectService.findSubjectByID(subjectDTO.getSubjectID())
//                    .orElseThrow(() -> new AppsException(AppsErrorCode.STUDENT_DOES_NOT_EXIST));
//        }
//
//        subject.setSubjectName(subjectDTO.getSubjectName());
//        return subject;
//    }
}
