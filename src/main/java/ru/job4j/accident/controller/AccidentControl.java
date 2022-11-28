package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

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
        model.addAttribute("accident", new Accident(0, "", "", "", null));
        return "accident/createAccident";
    }

    @PostMapping("/create")
    public String createAccidentPost(@ModelAttribute Accident accident) {
        accident.setCreated(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        accidents.create(accident);
        return "redirect:/index";
    }

    @GetMapping("/update")
    public String updateGet(@RequestParam("id") int id, Model model) {
        addAttrAccident(model, id);
        return "accident/updateAccident";
    }

    @PostMapping("/update")
    public String updatePost(@ModelAttribute Accident accident) {
        accident.setCreated(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        accidents.create(accident);
        return "redirect:/index";
    }

    private void addAttrAccident(Model model, int id) {
        Optional<Accident> accidentOptional = accidents.findById(id);
        if (accidentOptional.isPresent()) {
            model.addAttribute("accident", accidentOptional.get());
        } else {
            model.addAttribute("accident",
                    new Accident(0, "", "", "", LocalDateTime.now()));
        }
    }
}
