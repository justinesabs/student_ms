package com.example.student_ms.controller;

import com.example.student_ms.dtos.StudentDto;
import com.example.student_ms.entity.Student;
import com.example.student_ms.repository.StudentRepository;
import com.example.student_ms.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
public class StudentController {


    @Autowired
    private final StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students/view")
    public String viewStudents(Model model, Principal principal) {
        String username = principal.getName();

        Student loggedInStudent = studentRepository.findByUsername(username).orElse(null);
        model.addAttribute("student", loggedInStudent);

        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);

        return "students_view";
    }

    @GetMapping("/students")
    public String listStudents(Model model, Principal principal) {
        String username = principal.getName();

        Student loggedInStudent = studentRepository.findByUsername(username).orElse(null);
        model.addAttribute("student", loggedInStudent);

        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        model.addAttribute("student", new StudentDto());
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") StudentDto studentDto) {
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setUsername(studentDto.getUsername());
        student.setEmail(studentDto.getEmail());
        student.setPassword(studentDto.getPassword());
        studentService.savesStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setUsername(student.getUsername());
        dto.setEmail(student.getEmail());
        dto.setPassword(student.getPassword()); 
        model.addAttribute("student", dto);
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") StudentDto studentDto) {
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setFirstName(studentDto.getFirstName());
        existingStudent.setLastName(studentDto.getLastName());
        existingStudent.setUsername(studentDto.getUsername());
        existingStudent.setEmail(studentDto.getEmail());
        studentService.updateStudent(existingStudent);
        return "redirect:/students";
    }

    @PostMapping("/students/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }
}
