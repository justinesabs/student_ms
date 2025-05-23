package com.example.student_ms.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import com.example.student_ms.dtos.StudentDto;
import com.example.student_ms.entity.Student;
import com.example.student_ms.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentApiController {

    private final StudentService studentService;

    public StudentApiController(StudentService studentService) {
        this.studentService = studentService;
    }

    private StudentDto convertToDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setUsername(student.getUsername());
        dto.setEmail(student.getEmail());
        dto.setPassword(student.getPassword());
        return dto;
    }

    private Student convertToEntity(StudentDto dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setUsername(dto.getUsername());
        student.setEmail(dto.getEmail());
        student.setPassword(dto.getPassword());
        return student;
    }

    @GetMapping
    public List<StudentDto> getAllStudents() {
        return studentService.getAllStudents()
                             .stream()
                             .map(this::convertToDto)
                             .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable Long id) {
        return convertToDto(studentService.getStudentById(id));
    }

    @PostMapping
    public StudentDto createStudent(@RequestBody StudentDto studentDto) {
        Student student = convertToEntity(studentDto);
        return convertToDto(studentService.savesStudent(student));
    }

    @PutMapping("/{id}")
    public StudentDto updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        Student existing = studentService.getStudentById(id);

        existing.setFirstName(studentDto.getFirstName());
        existing.setLastName(studentDto.getLastName());
        existing.setUsername(studentDto.getUsername());
        existing.setEmail(studentDto.getEmail());
        existing.setPassword(studentDto.getPassword());

        return convertToDto(studentService.updateStudent(existing));
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "Student deleted successfully";
    }
}
