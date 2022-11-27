package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

@Controller
@AllArgsConstructor
@ThreadSafe
public class AccidentControl {
    private final AccidentService accidents;

    @GetMapping("/accident/{id}")
    public String accidentGet(Model model, @PathVariable("id") int id) {
        if (accidents.findById(id).isPresent()) {
            model.addAttribute("accident", accidents.findById(id).get());
        }
        return "accident";
    }

    @GetMapping("/create")
    public String createAccidentGet(Model model) {
        model.addAttribute("accident", new Accident(0, "", "", null));
        return "createAccident";
    }

    @PostMapping("/create")
    public String createAccidentPost(@ModelAttribute Accident accident) {
        accidents.create(accident);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String editAccidentGet(Model model, @PathVariable("id") int id) {
        if (accidents.findById(id).isPresent()) {
            model.addAttribute("accident", accidents.findById(id).get());
        }
        return "editAccident";
    }

    @PostMapping("/edit")
    public String editAccidentPost(@ModelAttribute Accident accident) {
        accidents.replace(accident);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident")
    public String update(@RequestParam("id") int id, Model model) {
        if (accidents.findById(id).isPresent()) {
            model.addAttribute("accident", accidents.findById(id).get());
        }
        return "accident/update";
    }

    @PostMapping("/updateAccident")
    public String save(@ModelAttribute Accident accident) {
        accidents.create(accident);
        return "redirect:/";
    }
}