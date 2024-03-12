package com.kagwe.kilmohigh.Repository;

import com.kagwe.kilmohigh.Entities.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;


@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {


    @Query(nativeQuery = true, value = "select * from classroom where class_name = :className")
    Optional<Classroom> findByClassName(String className);
}
