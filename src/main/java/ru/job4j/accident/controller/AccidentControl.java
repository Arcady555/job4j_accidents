package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;
import ru.job4j.accident.service.RuleService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@AllArgsConstructor
@ThreadSafe
@RequestMapping("/accidents")
public class AccidentControl {
    private final AccidentService accidents;
    private final AccidentTypeService types;
    private final RuleService rules;

    @GetMapping("/{id}")
    public String accidentGet(Model model, @PathVariable("id") int id) {
        String rsl = "accident/accident";
        addAttrAccident(model, id, rsl);
        return rsl;
    }

    @GetMapping("/create")
    public String createAccidentGet(Model model) {
        model.addAttribute("rules", rules.findAll());
        model.addAttribute("types", types.findAll());
        model.addAttribute("accident",
                new Accident(0, "", "", ""));
        return "accident/createAccident";
    }

    @PostMapping("/create")
    public String createAccidentPost(@ModelAttribute Accident accident, HttpServletRequest req) {
       /** if (!rules.toSetRules(accident, req)) {
            return "redirect:/accidents/set-rule";
        }
        accident.setType(types.get(accident.getType().getId())); */
        accidents.create(accident);
        return "redirect:/index";
    }

    @GetMapping("/update")
    public String updateGet(@RequestParam("id") int id, Model model) {
        model.addAttribute("rules", rules.findAll());
        model.addAttribute("types", types.findAll());
        String rsl = "accident/updateAccident";
        addAttrAccident(model, id, rsl);
        return rsl;
    }

    @PostMapping("/update")
    public String updatePost(@ModelAttribute Accident accident, HttpServletRequest req) {
      /**  if (!rules.toSetRules(accident, req)) {
            return "redirect:/accidents/set-rule";
        }
        accident.setType(types.get(accident.getType().getId())); */
        System.out.println(accident.getName());
        System.out.println(accident.getId());
        accidents.update(accident);
        System.out.println(accident.getName());
        System.out.println(accident.getId());
        return "redirect:/index";
    }

    @GetMapping("/page-not-found")
    public String pageNotFound() {
        return "pageNotFound";
    }

    @GetMapping("/set-rule")
    public String withoutRule() {
        return "setRule";
    }

    private void addAttrAccident(Model model, int id, String str) {
        Optional<Accident> accidentOptional = accidents.findById(id);
        if (accidentOptional.isPresent()) {
            model.addAttribute("accident", accidentOptional.get());
        } else {
            str = "redirect:/accidents/page-not-found";
        }
    }
}