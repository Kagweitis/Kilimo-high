package com.kagwe.kilmohigh.Services;


import com.kagwe.kilmohigh.DTO.ResponseDTO;
import com.kagwe.kilmohigh.Entities.Stream;
import com.kagwe.kilmohigh.Entities.Student;
import com.kagwe.kilmohigh.Repository.ClassroomRepository;
import com.kagwe.kilmohigh.Repository.StreamsRepository;
import com.kagwe.kilmohigh.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class KilimoResourceManagementService {

    private final StreamsRepository streamsRepository;
    private final StudentRepository studentRepository;
    private final ClassroomRepository classroomRepository;

    public ResponseEntity<?> createStream(Stream stream) {

        ResponseDTO response = new ResponseDTO();
        try {
            //check if that stream already exists
            Optional<Stream> existingStream = streamsRepository.findStreamByStreamName(stream.getStreamName());
            if (existingStream.isPresent()) {
                response.setStreams(Collections.singletonList(existingStream.get()));
                response.setCode(409);
                response.setMessage("A stream with that name already exists!");
            } else {
                streamsRepository.save(stream);
                response.setMessage("Stream added successfully");
                response.setCode(201);
            }
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            log.error("An error occured while creating stream " + e.getMessage());
            response.setMessage("An error occured while creating stream. Please try again later");
            response.setCode(500);
            return ResponseEntity.internalServerError().body(response);
        }
    }

    public ResponseEntity<?> viewAllClassStreams() {
        ResponseDTO response = new ResponseDTO();

        try {
            //check if there are any streams

            List<Stream> availableStreams = streamsRepository.findAllByDeletedFalse();

            if (availableStreams.isEmpty()) {
                response.setCode(404);
                response.setMessage("No streams in the system. Please add some to see them here");
            } else {
                response.setStreams(availableStreams);
                response.setMessage("Here are the streams in the system");
            }
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            log.error("An error occured while getting streams " + e.getMessage());
            response.setMessage("An error occured while getting streams. Please try again later");
            response.setCode(500);
            return ResponseEntity.internalServerError().body(response);
        }
    }

    public ResponseEntity<?> viewSingleClassStreams(String streamName) {

        ResponseDTO response = new ResponseDTO();

        try {
            //check if exists
            Optional<Stream> existingStream = streamsRepository.findStreamByStreamName(streamName);
            if (existingStream.isPresent()) {
                response.setStreams(Collections.singletonList(existingStream.get()));
                response.setCode(200);
                response.setMessage("Stream with that name found!");
            } else {
                response.setMessage("No stream with that name found");
                response.setCode(404);
            }
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            log.error("An error occured while getting the stream " + e.getMessage());
            response.setMessage("An error occured while getting the stream. Please try again later");
            response.setCode(500);
            return ResponseEntity.internalServerError().body(response);
        }
    }


    public ResponseEntity<?> createStudent(Student student) {

        ResponseDTO response = new ResponseDTO();


        try {

            //check if that student already exists
            Optional<Student> existingStudent = studentRepository.findStudentByAdmissionNo(student.getAdmissionNo());
            if (existingStudent.isPresent()) {
                response.setStudents(Collections.singletonList(student));
                response.setCode(409);
                response.setMessage("A student with that admission Number already exists!");
            } else {
                studentRepository.save(student);
                response.setStudents(Collections.singletonList(student));
                response.setMessage("Student created successfully");
                response.setCode(201);
            }
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            log.error("An error occured while creating student " + e.getMessage());
            response.setMessage("An error occured while creating student. Please try again later");
            response.setCode(500);
            return ResponseEntity.internalServerError().body(response);
        }
    }


    public ResponseEntity<?> editStudentInformation(Student student) {
        ResponseDTO response = new ResponseDTO();

        try {
            Optional<Student> existingStudent = studentRepository.findStudentByAdmissionNo(student.getAdmissionNo());
            if (existingStudent.isPresent()) {
                existingStudent.get().setAdmissionNo(student.getAdmissionNo());
                existingStudent.get().setClassName(student.getClassName());
                existingStudent.get().setLastName(student.getLastName());
                existingStudent.get().setFirstName(student.getFirstName());
                existingStudent.get().setStreamName(student.getStreamName());
                studentRepository.save(existingStudent.get());
                response.setStudents(Collections.singletonList(student));
//                response.setClasses(Collections.singletonList(classroomRepository.findByClassName(student.getClassName()).get()));
//                response.setStreams(Collections.singletonList(streamsRepository.findStreamByStreamName(student.getStreamName()).get()));
                response.setCode(200);
                response.setMessage("Student Details edited successfully!");
            } else {
                response.setMessage("No student with that admission number found");
                response.setCode(404);
            }
            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            log.error("An error occured while editing student info " + e);
            response.setMessage("An error occured while editing student info. Please try again later");
            response.setCode(500);
            return ResponseEntity.internalServerError().body(response);
        }
    }


    public ResponseEntity<?> deleteStudent(String admissionNo) {
        ResponseDTO response = new ResponseDTO();

        try {
            Optional<Student> existingStudent = studentRepository.findStudentByAdmissionNo(admissionNo);
            if (existingStudent.isPresent()){
                existingStudent.get().setDeleted(true);
                studentRepository.save(existingStudent.get());
                response.setCode(200);
                response.setMessage("student details deleted successfully");
            } else {
                response.setMessage("No student with that admission number found");
                response.setCode(404);
            }

            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            log.error("An error occured while editing student info " + e.getMessage());
            response.setMessage("An error occured while deleting student info. Please try again later");
            response.setCode(500);
            return ResponseEntity.internalServerError().body(response);
        }
    }

    public ResponseEntity<?> viewSpecificStudentData(String admissionNo){
        ResponseDTO response = new ResponseDTO();

        try {
            Optional<Student> existingStudent = studentRepository.findStudentByAdmissionNo(admissionNo);
            if (existingStudent.isPresent()){
                response.setStudents(Collections.singletonList(existingStudent.get()));
                response.setCode(200);
                response.setMessage("student details found successfully");
            } else {
                response.setMessage("No student with that admission number found");
                response.setCode(404);
            }
            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            log.error("An error occured while getting student info " + e.getMessage());
            response.setMessage("An error occured while getting student info. Please try again later");
            response.setCode(500);
            return ResponseEntity.internalServerError().body(response);
        }
    }

    public ResponseEntity<?> viewAllStudentData() {
        ResponseDTO response = new ResponseDTO();

        try{
            //find all
            List<Student> students = studentRepository.findAllByDeletedFalse();

            if (students.isEmpty()){
                response.setCode(404);
                response.setMessage("no students found");
            } else {
                response.setMessage("Students found!");
                response.setStudents(students);
            }
            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            log.error("An error occured while getting students info " + e.getMessage());
            response.setMessage("An error occured while getting students info. Please try again later");
            response.setCode(500);
            return ResponseEntity.internalServerError().body(response);
        }
    }

    public ResponseEntity<?> viewStudentFromStream(String streamName) {
        ResponseDTO response = new ResponseDTO();

        try {
            List<Student> students = studentRepository.findAllByStreamName(streamName);
            if (students.isEmpty()){
                response.setMessage("No students from that stream found");
                response.setCode(404);
            } else {
                response.setStudents(students);
                response.setCode(200);
                response.setMessage("Student found!");
            }
            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            log.error("An error occured while getting students from stream " + e.getMessage());
            response.setMessage("An error occured while getting from stream " + streamName + ". Please try again later");
            response.setCode(500);
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
