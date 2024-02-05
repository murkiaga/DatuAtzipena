package com.apirest.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.app.model.Cyclist;

public interface CyclistRepository extends JpaRepository<Cyclist, Long>{
	
}
