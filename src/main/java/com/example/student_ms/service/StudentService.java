package com.example.student_ms.service;

import java.util.List;
import com.example.student_ms.entity.Student;

public interface StudentService {

    List<Student> getAllStudents();

    Student savesStudent(Student student);

    Student getStudentById(long id);

    Student updateStudent(Student student);
    
    void deleteStudentById(long id);
}
