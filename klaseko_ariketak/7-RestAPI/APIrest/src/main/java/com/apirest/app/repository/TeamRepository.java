package com.apirest.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.app.model.Team;

public interface TeamRepository extends JpaRepository <Team,Long>{

}