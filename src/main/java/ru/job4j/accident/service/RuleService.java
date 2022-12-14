package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleRepository;

import java.util.*;

@Service
@ThreadSafe
@AllArgsConstructor
public class RuleService {
    private RuleRepository ruleStore;

    public Iterable<Rule> findAll() {
        return ruleStore.findAll();
    }

    public Optional<Rule> findById(int key) {
        return ruleStore.findById(key);
    }
}