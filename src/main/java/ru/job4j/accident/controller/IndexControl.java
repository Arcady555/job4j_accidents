package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.service.AccidentService;

@Controller
@AllArgsConstructor
@ThreadSafe
@RequestMapping("/accident")
public class IndexControl {

    private final AccidentService accidentService;

    @GetMapping("/index")
    public String indexGet(Model model) {
        model.addAttribute("accidents", accidentService.findAll());
        return "index";
    }
}
