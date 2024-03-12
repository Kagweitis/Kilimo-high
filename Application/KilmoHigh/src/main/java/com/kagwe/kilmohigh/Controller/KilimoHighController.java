package com.kagwe.kilmohigh.Controller;


import com.kagwe.kilmohigh.Entities.Stream;
import com.kagwe.kilmohigh.Entities.Student;
import com.kagwe.kilmohigh.Services.KilimoResourceManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class KilimoHighController {

    private final KilimoResourceManagementService kilimoResourceManagementService;


    @PostMapping("/create_stream")
    public ResponseEntity<?> createStream(@RequestBody Stream stream){
        return kilimoResourceManagementService.createStream(stream);
    }

    @GetMapping("/view_streams")
    public ResponseEntity<?> viewAllClassStreams(){
        return kilimoResourceManagementService.viewAllClassStreams();
    }

    @GetMapping("/view_single_stream")
    public ResponseEntity<?> viewSingleStream(@RequestParam String streamName){
        return kilimoResourceManagementService.viewSingleClassStreams(streamName);
    }

    @PostMapping("/create_student")
    public ResponseEntity<?> createStudent(@RequestBody Student student){
        return kilimoResourceManagementService.createStudent(student);
    }

    @PutMapping("edit_student")
    public ResponseEntity<?> editStudentInformation(@RequestBody Student student){
        return kilimoResourceManagementService.editStudentInformation(student);
    }

    @PatchMapping("delete_student")
    public ResponseEntity<?> deleteStudent(@RequestParam String admissionNo) {
        return kilimoResourceManagementService.deleteStudent(admissionNo);
    }

    @GetMapping("/view_specific_student")
    public ResponseEntity<?> viewSpecificStudent(@RequestParam String admissionNo){
        return kilimoResourceManagementService.viewSpecificStudentData(admissionNo);
    }

    @GetMapping("/view_all_students")
    public ResponseEntity<?> viewAllStudents(){
        return kilimoResourceManagementService.viewAllStudentData();
    }

    @GetMapping("/students_from_stream")
    public ResponseEntity<?> viewStudentsFromStream(@RequestParam String streamName){
        return kilimoResourceManagementService.viewStudentFromStream(streamName);
    }

}
