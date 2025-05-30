package com.example.student_ms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.student_ms.entity.Student;


@Repository
public interface StudentRepository  extends JpaRepository<Student, Long>{
    Optional<Student> findByUsername(String username);
}


    
