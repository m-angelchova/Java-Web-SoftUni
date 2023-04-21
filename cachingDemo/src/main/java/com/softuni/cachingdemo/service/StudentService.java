package com.softuni.cachingdemo.service;

import com.softuni.cachingdemo.model.StudentDTO;
import com.softuni.cachingdemo.repository.StudentRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements StudentServiceInterface{
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Cacheable("students")
    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAllStudents();
    }

    @Cacheable("students")
    @Override
    public Optional<StudentDTO> getStudentByName(String name) {
        return studentRepository.findAllStudents().stream().filter(s -> s.getName().equals(name)).findAny();
    }

    @CacheEvict(cacheNames = "students", allEntries = true)
    @Override
    public void refreshStudents() {

    }
}
