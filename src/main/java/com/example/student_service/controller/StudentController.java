package com.example.student_service.controller;

import com.example.student_service.VO.ResponseTemplateVO;
import com.example.student_service.entity.Student;
import com.example.student_service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public class StudentController {
    @Autowired
    private StudentService studentService;

    //Save
    @PostMapping("/")
    public Student saveUser(@RequestBody Student student){

        return studentService.saveStudent(student);
    }


    //Get All User and Department by id
    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id")
                                                            Long studentId){
        return studentService.getUserWithDepartment(studentId);
    }
}
