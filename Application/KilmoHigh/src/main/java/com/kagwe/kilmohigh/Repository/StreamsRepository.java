package com.kagwe.kilmohigh.Repository;

import com.kagwe.kilmohigh.Entities.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StreamsRepository extends JpaRepository<Stream, Long> {


    @Query(nativeQuery = true, value = "select * from stream where stream_name = :streamName and deleted = false")
    Optional<Stream> findStreamByStreamName(String streamName);

    @Query(nativeQuery = true, value = "select * from stream where deleted = false")
    List<Stream> findAllByDeletedFalse();

}
