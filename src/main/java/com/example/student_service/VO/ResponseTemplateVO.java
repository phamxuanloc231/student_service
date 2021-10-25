package com.example.student_service.VO;

import com.example.student_service.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    private Student student;
    private com.example.student_service.VO.Department department;
}
