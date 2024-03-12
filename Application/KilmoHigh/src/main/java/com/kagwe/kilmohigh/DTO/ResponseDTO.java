package com.kagwe.kilmohigh.DTO;

import com.kagwe.kilmohigh.Entities.Classroom;
import com.kagwe.kilmohigh.Entities.Stream;
import com.kagwe.kilmohigh.Entities.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    private String message;
    private int code;
    private List<Student> students;
    private List<Classroom> classes;
    private List<Stream> streams;
}
