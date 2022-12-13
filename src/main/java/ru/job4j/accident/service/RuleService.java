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

    public boolean toSetRules(Accident accident, HttpServletRequest req) {
        boolean rsl = false;
        Set<Rule> set = new HashSet<>();
        String[] ids = req.getParameterValues("rIds");
        if (ids != null) {
            for (String str : ids) {
                Optional<Rule> ruleOptional = findById(Integer.parseInt(str));
                if (ruleOptional.isPresent()) {
                    Rule rule = ruleOptional.get();
                    set.add(rule);
                    rsl = true;
                }
            }
        }
        accident.setRules(set);
        return rsl;
    }
}