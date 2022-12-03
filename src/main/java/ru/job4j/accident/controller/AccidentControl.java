package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;

import java.util.Optional;

@Controller
@AllArgsConstructor
@ThreadSafe
@RequestMapping("/accidents")
public class AccidentControl {
    private static int createId = 0;
    private final AccidentService accidents;
    private final AccidentTypeService types;

    @GetMapping("/{id}")
    public String accidentGet(Model model, @PathVariable("id") int id) {
        String rsl = "accident/accident";
        addAttrAccident(model, id, rsl);
        return rsl;
    }

    @GetMapping("/create")
    public String createAccidentGet(Model model) {
        model.addAttribute("types", types.findAll());
        model.addAttribute("accident",
                new Accident(0, "", "", "", null, null));
        return "accident/createAccident";
    }

    @PostMapping("/create")
    public String createAccidentPost(@ModelAttribute Accident accident) {
        accident.setId(createId++);
        accident.setType(types.get(accident.getType().getId()));
        accidents.create(accident);
        return "redirect:/index";
    }

    @GetMapping("/update")
    public String updateGet(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", types.findAll());
        String rsl = "accident/updateAccident";
        addAttrAccident(model, id, rsl);
        return rsl;
    }

    @PostMapping("/update")
    public String updatePost(@ModelAttribute Accident accident) {
        accident.setType(types.get(accident.getType().getId()));
        accidents.update(accident);
        return "redirect:/index";
    }

    @GetMapping("/page-not-found")
    public String pageNotFound() {
        return "pageNotFound";
    }

    private void addAttrAccident(Model model, int id, String str) {
        Optional<Accident> accidentOptional = accidents.findById(id);
        if (accidentOptional.isPresent()) {
            model.addAttribute("accident", accidentOptional.get());
        } else {
            str = "redirect:/page-not-found";
        }
    }
}
