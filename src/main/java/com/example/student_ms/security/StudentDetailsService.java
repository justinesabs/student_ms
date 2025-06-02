package com.example.student_ms.security;

import com.example.student_ms.entity.Student;
import com.example.student_ms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class StudentDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentDetailsService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Student not found"));

        return new org.springframework.security.core.userdetails.User(

        
            student.getUsername(),
            student.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
