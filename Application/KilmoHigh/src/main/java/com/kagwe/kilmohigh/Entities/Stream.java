package com.kagwe.kilmohigh.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Stream {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String streamId;

    @Column(name = "stream_name", nullable = false)
    private String streamName;

    @OneToMany(targetEntity = Classroom.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "stream_name", referencedColumnName = "stream_name")
    private List<Classroom> classes;

    @Builder.Default
    private Boolean deleted = false;

    @OneToMany(targetEntity = Student.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "stream_name", referencedColumnName = "stream_name")
    private List<Student> students;

}
