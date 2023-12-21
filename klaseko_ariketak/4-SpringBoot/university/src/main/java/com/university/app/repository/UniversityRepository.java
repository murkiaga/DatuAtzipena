package com.university.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.app.model.University;

public interface UniversityRepository extends JpaRepository<University, Integer> {

}
