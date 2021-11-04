package com.example.student_service.service;

import com.example.student_service.VO.Department;
import com.example.student_service.VO.ResponseTemplateVO;
import com.example.student_service.entity.Student;
import com.example.student_service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.client.RestTemplate;

public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
    @CacheEvict(value = "getUserWithOrder", allEntries = true)
    public void evictAllCacheValues() {}

    @Cacheable(value = "getUserWithOrder")
    public ResponseTemplateVO getUserWithDepartment(Long studentId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Student student = studentRepository.findById(studentId).get();
        vo.setStudent(student);
        Department department =
                restTemplate.getForObject("http://localhost:9001/department/"
                                + student.getDepartmentId(),
                        Department.class);

        vo.setDepartment(department);

        return vo;
    }
}
