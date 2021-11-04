package com.example.student_service.controller;

import com.example.student_service.VO.ResponseTemplateVO;
import com.example.student_service.entity.Student;
import com.example.student_service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/students")

public class StudentController {
    @Autowired
    private StudentService studentService;

    //Save
    @PostMapping("/")
    public Student saveUser(@RequestBody Student student){

        return studentService.saveStudent(student);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseTemplateVO> getUserWithDepartment(@PathVariable("id")
                                                                            Long userId){

        return studentService.getUserWithOrder(userId);
    }
}
