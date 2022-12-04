package ru.job4j.accident.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
@ThreadSafe
public class RuleService {
    private final Map<Integer, Rule> rules;

    public RuleService(Map<Integer, Rule> rules) {
        this.rules = Map.of(
                1, new Rule(1, "Статья. 1"),
                2, new Rule(2, "Статья. 2"),
                3, new Rule(3, "Статья. 3")
        );
    }

    public Collection<Rule> findAll() {
        return rules.values();
    }

    public Optional<Rule> findById(int key) {
        return Optional.ofNullable(rules.get(key));
    }
}