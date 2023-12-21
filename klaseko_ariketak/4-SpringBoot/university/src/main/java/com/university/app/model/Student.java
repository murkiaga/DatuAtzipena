package com.university.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="student")
public class Student {
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY) //La opción más usada con MySQL
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="location")
	private String lastname;
	
	@ManyToOne
    @JoinColumn(name="university_id")
    private University university;
	
	public Student(String name, String lastname) {
		this.name = name;
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", lastname=" + lastname + ", university=" + university + "]";
	}
	
	public void desmatrikulatu() {
		this.university = null;
	}
	
	

}
