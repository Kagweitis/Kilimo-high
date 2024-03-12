package com.kagwe.kilmohigh.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String admissionNo;

    @Builder.Default
    private Boolean deleted = false;

    @Column(name = "class_name", nullable = false)
    private String className;

    @Column(name = "stream_name", nullable = false)
    private String streamName;



}
