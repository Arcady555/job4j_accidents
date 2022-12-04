package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;
import ru.job4j.accident.service.RuleService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
                new Accident(0, "", "", "", null, null, null));
        return "accident/createAccident";
    }

    @PostMapping("/create")
    public String createAccidentPost(@ModelAttribute Accident accident, HttpServletRequest req) {
        toSetRules(accident, req);
        accident.setType(types.get(accident.getType().getId()));
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
        toSetRules(accident, req);
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

    private void toSetRules(Accident accident, HttpServletRequest req) {
        Set<Rule> set = new HashSet<>();
        String[] ids = req.getParameterValues("rIds");
        if (ids != null) {
            for (String str : ids) {
                Optional<Rule> ruleOptional = rules.findById(Integer.parseInt(str));
                if (ruleOptional.isPresent()) {
                    Rule rule = ruleOptional.get();
                    set.add(rule);
                } else {
                    set.add(new Rule(0, ""));
                }
            }
        } else {
            set.add(new Rule(0, ""));
        }
        accident.setRules(set);
    }
}
