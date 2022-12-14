package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleStore;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@ThreadSafe
@AllArgsConstructor
public class RuleService {
    private RuleStore ruleStore;
    public Collection<Rule> findAll() {
        return ruleStore.findAll();
    }

    public Optional<Rule> findById(int key) {
        return ruleStore.findById(key);
    }
}