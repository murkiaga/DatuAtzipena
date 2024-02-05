package com.apirest.app.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cyclist")
public class Cyclist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="birth_date")
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
	
	private String nationality;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn (name = "team_id")
	private Team team;
	
	//Gehitu 'teamName' atributua JSONari
	@JsonProperty("teamName")
	public String getTeamName() {
		if (team != null) {
			return team.getName();
		} else {
			return null;
		}
	}

	public Cyclist(String firstName, String lastName, LocalDate birthDate, String nationality, Team team) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.nationality = nationality;
		this.team = team;
	}
}
