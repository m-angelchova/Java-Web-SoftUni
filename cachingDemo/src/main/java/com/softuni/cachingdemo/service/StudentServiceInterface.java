package com.softuni.cachingdemo.service;

import com.softuni.cachingdemo.model.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentServiceInterface {
    List<StudentDTO> getAllStudents();
    Optional<StudentDTO> getStudentByName(String name);
    void refreshStudents();
}
