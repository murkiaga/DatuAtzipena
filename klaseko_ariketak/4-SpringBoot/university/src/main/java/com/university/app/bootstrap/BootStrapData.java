package com.university.app.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.university.app.model.Student;
import com.university.app.model.University;
import com.university.app.repository.StudentRepository;
import com.university.app.repository.UniversityRepository;

import jakarta.transaction.Transactional;

@Component
public class BootStrapData implements CommandLineRunner{
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private UniversityRepository universityRepo;
	
	//@Transactional
	@Override
	public void run (String... arg0) throws Exception{
		
		Student student1 = new Student("Mikel", "Urkiaga");
		Student student2 = new Student("Jon", "Braun");
		Student student3 = new Student("Pepito", "Grillo");
		University university1 = new University("EHU", "Donostia");
		University university2 = new University("Deustu", "Bilbo");
		
		student1.setUniversity(university1);
		student2.setUniversity(university1);
		student3.setUniversity(university2);
		
		universityRepo.save(university2);
		universityRepo.save(university1);
		studentRepo.save(student1);
		studentRepo.save(student2);
		studentRepo.save(student3);
		
	}
}
