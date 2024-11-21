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

    private final List<Erabiltzailea> erabiltzaileak = new ArrayList<>();

    public ErabiltzaileaController() {
        erabiltzaileak.add(new Erabiltzailea(1, "Asier", "Aldekoa", "a_aldekoa@fpzornotza.com"));
        erabiltzaileak.add(new Erabiltzailea(2, "Ugaitz", "Bilbao", "u_bilbao@fpzornotza.com"));
        erabiltzaileak.add(new Erabiltzailea(3, "Josu", "Foruria", "j_foruria@fpzornotza.com"));
        erabiltzaileak.add(new Erabiltzailea(4, "Egoitz", "Urrutia", "e_urrutia@fpzornotza.com"));
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
