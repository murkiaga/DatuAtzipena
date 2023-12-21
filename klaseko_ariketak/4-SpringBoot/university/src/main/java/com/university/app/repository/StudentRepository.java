package com.university.app.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.app.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	List<Student> findByNameContainingIgnoreCaseOrLastnameContainingIgnoreCase(String name, String lastname);
}
