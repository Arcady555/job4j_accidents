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
@RequestMapping("/accidents")
public class AccidentControl {
    private final AccidentService accidents;

    @GetMapping("/{id}")
    public String accidentGet(Model model, @PathVariable("id") int id) {
        addAttrAccident(model, id);
        return "accident/accident";
    }

    @GetMapping("/create")
    public String createAccidentGet(Model model) {
        model.addAttribute("accident", new Accident(0, "", "", null));
        return "accident/createAccident";
    }

    @PostMapping("/create")
    public String createAccidentPost(@ModelAttribute Accident accident) {
        accidents.create(accident);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")  /** Лишний метод, удалить(?) в конце проекта */
    public String editAccidentGet(Model model, @PathVariable("id") int id) {
        addAttrAccident(model, id);
        return "accident/editAccident";
    }

    @PostMapping("/edit")   /** Лишний метод, удалить(?) в конце проекта */
    public String editAccidentPost(@ModelAttribute Accident accident) {
        accidents.replace(accident);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident")
    public String updateGet(@RequestParam("id") int id, Model model) {
        addAttrAccident(model, id);
        return "accident/formUpdateAccident";
    }

    @PostMapping("/formUpdateAccident")
    public String updatePost(@ModelAttribute Accident accident) {
        accidents.create(accident);
        return "redirect:/index";
    }

    private void addAttrAccident(Model model, int id) {
        accidents.findById(id).ifPresent(
                accident -> model.addAttribute("accident", accident)
        );
    }
}