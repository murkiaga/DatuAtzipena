package com.university.app.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="university")
public class University {
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY) //La opción más usada con MySQL
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="location")
	private String location;
	
	@Column(nullable = true, length = 64)
	private String irudia;
	
	@OneToMany(mappedBy="university")
    private List<Student> students = new ArrayList();
	
	public University(String name, String location) {
		this.name = name;
		this.location = location;
	}
	
	@Transient
	public String getIrudiaImagePath() {
		if (irudia == null || id < 1) return null;
		return "/university-photos/" + id + "/" + irudia;
	}
}
