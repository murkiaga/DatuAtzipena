package com.DatuAtzipena.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.DatuAtzipena.app.modeloak.Erabiltzailea;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ErabiltzaileaController {
	//UGAITZEK EGINDA
    private final List<Erabiltzailea> erabiltzaileak = new ArrayList<>();

    public ErabiltzaileaController() {
        erabiltzaileak.add(new Erabiltzailea(1, "Asier", "Pepito", "a_pepito@fpzornotza.com"));
        erabiltzaileak.add(new Erabiltzailea(2, "Ugaitz", "Pepito2", "u_pepito2@fpzornotza.com"));
        erabiltzaileak.add(new Erabiltzailea(3, "Josu", "Pepito3", "j_pepito3@fpzornotza.com"));
        erabiltzaileak.add(new Erabiltzailea(4, "Egoitz", "Pepito4", "e_pepito4@fpzornotza.com"));
    }

    @GetMapping("/erabiltzaileak")
    public String erakutsiErabiltzaileak(Model model) {
        model.addAttribute("erabiltzaileak", erabiltzaileak);
        return "erabiltzaileak"; 
    }

    @GetMapping("/erabiltzailea/{id}")
    public String erakutsiErabiltzailea(@PathVariable int id, Model model) {
        Erabiltzailea erabiltzailea = null;

        for (Erabiltzailea e : erabiltzaileak) {
            if (e.getId() == id) {
                erabiltzailea = e;
                break; 
            }
        }

        model.addAttribute("erabiltzailea", erabiltzailea);
        return "erabiltzailea"; 
    }
}
