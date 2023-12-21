package com.university.app.model.service;

import org.springframework.stereotype.Component;

@Component //Edo @Service
public class MyService {

	public String operacion() {
		return "Ejecutando algo...";
	}
}
