package com.example.student_service.service;

import com.example.student_service.ExceptionEntity.ExceptionCustom;
import com.example.student_service.VO.Department;
import com.example.student_service.VO.ResponseTemplateVO;
import com.example.student_service.entity.Student;
import com.example.student_service.repository.StudentRepository;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
    private int flag = 0;

    @RateLimiter(name = "basicExample",fallbackMethod = "fallBackRateLimiter")
    public ResponseEntity<ResponseTemplateVO> getUserWithOrder(Long userId) {
        flag = flag +1;
        System.out.println("System retry count: "+flag);
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Student user =studentRepository.findById(userId).get();
        vo.setStudent(user);
        Department order =
                restTemplate.getForObject("http://localhost:9001/department/"
                                + user.getDepartmentId(),
                        Department.class);

        vo.setDepartment(order);
        return new ResponseEntity<ResponseTemplateVO>(vo, HttpStatus.OK);
    }

    public ResponseEntity<ExceptionCustom> fallBackRateLimiter(RuntimeException e){
        flag = 0;
        System.out.println("Fall Back Ratelimiter: "+e.getMessage());
        ResponseTemplateVO vo = new ResponseTemplateVO();
        ExceptionCustom exceptionCustom = new ExceptionCustom(
                "Fall Back Ratelimiter Items Service is Down",e.getMessage());
        return new ResponseEntity<ExceptionCustom>(exceptionCustom, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
