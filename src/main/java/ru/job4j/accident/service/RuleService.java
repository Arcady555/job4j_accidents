package ru.job4j.accident.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

    public boolean toSetRules(Accident accident, HttpServletRequest req) {
        boolean rsl = false;
        Set<Rule> set = new HashSet<>();
        String[] ids = req.getParameterValues("rIds");
        if (ids != null) {
            for (String str : ids) {
                Optional<Rule> ruleOptional = findById(Integer.parseInt(str));
                if (ruleOptional.isEmpty()) {
                    return false;
                }
                Rule rule = ruleOptional.get();
                set.add(rule);
                rsl = true;
            }
        }
        accident.setRules(set);
        return rsl;
    }
}