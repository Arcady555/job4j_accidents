package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String indexGet(Model model) {
        model.addAttribute("user", "Petr Arsentev");
        return "index";
    }
}
