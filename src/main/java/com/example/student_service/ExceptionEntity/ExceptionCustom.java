package com.example.student_service.ExceptionEntity;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExceptionCustom {
    private String YourErrorStatus;
    private String MessageException;
}
