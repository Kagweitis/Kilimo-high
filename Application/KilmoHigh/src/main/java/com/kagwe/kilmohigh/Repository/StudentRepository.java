package com.kagwe.kilmohigh.Repository;

import com.kagwe.kilmohigh.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    @Query(nativeQuery = true, value = "select * from student where admission_no = :admissionNo and deleted = false")
    Optional<Student> findStudentByAdmissionNo(String admissionNo);


    @Query(nativeQuery = true, value = "select * from student where deleted = false")
    List<Student> findAllByDeletedFalse();


    @Query(nativeQuery = true, value = "select * from student where stream_name = :streamName and deleted = false")
    List<Student> findAllByStreamName(String streamName);
}
