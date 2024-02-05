package com.apirest.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.app.repository.TeamRepository;
import com.apirest.app.model.Team;

@RestController
@RequestMapping("/api/team")
public class TeamController {

	@Autowired
	private TeamRepository teamRepo;
	
	@GetMapping({"/",""})
	public List<Team> getTeams() {	
		return teamRepo.findAll();
	}
}
