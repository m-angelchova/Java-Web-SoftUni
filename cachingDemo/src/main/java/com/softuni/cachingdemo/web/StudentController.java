package com.softuni.cachingdemo.web;

import com.softuni.cachingdemo.model.StudentDTO;
import com.softuni.cachingdemo.service.StudentServiceInterface;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    private final StudentServiceInterface service;

    public StudentController(StudentServiceInterface service) {
        this.service = service;
    }


//    @Cacheable("students")
    @GetMapping("/students/all")
    public ResponseEntity<List<StudentDTO>> findAll(){

        List<StudentDTO> studentDTOS = service.getAllStudents();

        studentDTOS.forEach(System.out::println);

        return ResponseEntity.ok(studentDTOS);
    }

    @GetMapping("/students/all/evict")
    public ResponseEntity<List<StudentDTO>> findAllAndEvict(){

        List<StudentDTO> studentDTOS = service.getAllStudents();

        service.refreshStudents();

        return ResponseEntity.ok(studentDTOS);
    }

    @GetMapping("/students/find")
    public ResponseEntity<StudentDTO> findStudentByName(@RequestParam("q") String q){

        Optional<StudentDTO> foundStudent = service.getStudentByName(q);
        return foundStudent
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
