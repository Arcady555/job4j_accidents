package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;
import ru.job4j.accident.service.RuleService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
@AllArgsConstructor
@ThreadSafe
@RequestMapping("/accidents")
public class AccidentControl {
    private final AccidentService accidents;
    private final AccidentTypeService types;
    private final RuleService ruleService;

    @GetMapping("/{id}")
    public String accidentGet(Model model, @PathVariable("id") int id) {
        String rsl = "accident/accident";
        addAttrAccident(model, id, rsl);
        return rsl;
    }

    @GetMapping("/create")
    public String createAccidentGet(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("rules", ruleService.findAll());
        model.addAttribute("types", types.findAll());
        model.addAttribute("accident",
                new Accident(0, "", "", "", null, null, null));
        return "accident/createAccident";
    }

    @PostMapping("/create")
    public String createAccidentPost(@ModelAttribute Accident accident, HttpServletRequest req) {
        if (!fullTask(accident, req)) {
            return "redirect:/accidents/set-rule";
        }
        accidents.save(accident);
        return "redirect:/index";
    }

    @GetMapping("/update")
    public String updateGet(@RequestParam("id") int id, Model model) {
        model.addAttribute("rules", ruleService.findAll());
        model.addAttribute("types", types.findAll());
        String rsl = "accident/updateAccident";
        addAttrAccident(model, id, rsl);
        return rsl;
    }

    @PostMapping("/update")
    public String updatePost(@ModelAttribute Accident accident, HttpServletRequest req) {
        if (!fullTask(accident, req)) {
            return "redirect:/accidents/set-rule";
        }
        accidents.save(accident);
        return "redirect:/index";
    }

    @GetMapping("/page-not-found")
    public String pageNotFound(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "pageNotFound";
    }

    @GetMapping("/set-rule")
    public String withoutRule(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "setRule";
    }

    private void addAttrAccident(Model model, int id, String str) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Optional<Accident> accidentOptional = accidents.findById(id);
        if (accidentOptional.isPresent()) {
            model.addAttribute("accident", accidentOptional.get());
        } else {
            str = "redirect:/accidents/page-not-found";
        }
    }

    private boolean fullTask(Accident accident, HttpServletRequest req) {
        boolean rsl = false;
        Set<Rule> rules = new HashSet<>();
        String[] rIds = req.getParameterValues("rIds");
        if (rIds != null) {
            for (String str : rIds) {
                int ruleId = Integer.parseInt(str);
                Optional<Rule> ruleOptional = ruleService.findById(ruleId);
                if (ruleOptional.isEmpty()) {
                    return false;
                }
                Rule rule = ruleOptional.get();
                rules.add(rule);
                rsl = true;
            }
        }
        accident.setRules(rules);
        accident.setCreated(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return rsl;
    }
}