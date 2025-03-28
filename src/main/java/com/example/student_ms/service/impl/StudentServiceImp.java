package com.example.student_ms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.student_ms.entity.Student;
import com.example.student_ms.service.StudentService;
import com.example.student_ms.repository.StudentRepository;


@Service
public class StudentServiceImp implements StudentService{

    private StudentRepository studentRepository;

    public StudentServiceImp(StudentRepository studentRepository) {
        super();
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student savesStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }
    
    public void deleteStudentById(long id) {
        studentRepository.deleteById(id);
    }
    
}
