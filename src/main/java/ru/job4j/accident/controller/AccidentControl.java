package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

@Controller
@AllArgsConstructor
@ThreadSafe
public class AccidentControl {
    private final AccidentService accidents;

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
        model.addAttribute("accident", accidents.findById(id));
        return "editAccident";
    }

    @PostMapping("/edit")
    public String editAccidentPost(@ModelAttribute Accident accident) {
        accidents.replace(accident);
        return "redirect:/index";
    }
}

