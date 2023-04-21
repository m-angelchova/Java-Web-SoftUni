package com.softuni.cachingdemo.repository;

import com.softuni.cachingdemo.model.StudentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
    private Logger LOGGER = LoggerFactory.getLogger(StudentRepository.class);

    public List<StudentDTO> findAllStudents (){
        LOGGER.info("Downloading students...");
        dummyWait();
        LOGGER.info("Students downloaded!");
        return List.of(
                new StudentDTO().setName("Adrian").setAge(22),
                new StudentDTO().setName("Howdy").setAge(69)
        );
    }

    private void dummyWait(){

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
