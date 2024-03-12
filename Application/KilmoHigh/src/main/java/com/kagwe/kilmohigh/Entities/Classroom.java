package com.kagwe.kilmohigh.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String classTeacher;

    @Column(name = "class_name", nullable = false)
    private String className;

    @Column(name = "stream_name", nullable = false)
    private String streamName;

    @OneToMany(targetEntity = Student.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "class_name", referencedColumnName = "class_name")
    private List<Student> students;

    @Builder.Default
    private Boolean deleted = false;
}
