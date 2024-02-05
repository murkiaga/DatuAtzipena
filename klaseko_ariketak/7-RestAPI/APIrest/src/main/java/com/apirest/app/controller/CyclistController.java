package com.apirest.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.app.model.Cyclist;
import com.apirest.app.model.Team;
import com.apirest.app.repository.CyclistRepository;
import com.apirest.app.repository.TeamRepository;


@RestController
@RequestMapping("/api/cyclist")
public class CyclistController {

	@Autowired
	private CyclistRepository cyclistRepo;
	
	@Autowired
	private TeamRepository teamRepo;
	
	@GetMapping({"/", ""})
	public List<Cyclist> getCyclist() {	
		return cyclistRepo.findAll();
	}
	
	@PostMapping("/new")
	public Cyclist newCyclistNoTeam(@RequestBody Cyclist newCyclist) {
	    return cyclistRepo.save(newCyclist);
	}
	
	@PostMapping("/new/teamId/{team_id}")
	public Cyclist newCyclist(@PathVariable Long team_id, @RequestBody Cyclist newCyclist) {
		Team nireTeam = teamRepo.getReferenceById(team_id);
		newCyclist.setTeam(nireTeam);
	    return cyclistRepo.save(newCyclist);
	}

}
