package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.AuthorityRepository;
import ru.job4j.accident.repository.UserRepository;

@AllArgsConstructor
@Controller
@ThreadSafe
public class RegControl {

    private final PasswordEncoder encoder;
    private final UserRepository users;
    private final AuthorityRepository authorities;

    @PostMapping("/registration")
    public String regSave(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String regPage() {
        return "registration";
    }
}