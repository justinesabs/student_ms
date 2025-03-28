package com.example.student_ms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.student_ms.entity.Student;
import com.example.student_ms.repository.StudentRepository;

@SpringBootApplication
public class StudentMsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StudentMsApplication.class, args);
	}
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {
		
		/**Student student1 = new Student("Justine", "Sabs", "justinesabs12@gmail.com");
		studentRepository.save(student1);

		Student student2 = new Student("Jade", "Rabs", "jaderabs12@gmail.com");
		studentRepository.save(student2);

		Student student3 = new Student("Dan", "Hips", "danhips12@gmail.com");
		studentRepository.save(student3);**/
	}


}
