package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleStore;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
@ThreadSafe
public class RuleService {
    private final RuleStore rules;

    public Collection<Rule> findAll() {
        return rules.findAll();
    }

    public Optional<Rule> findById(int id) {
        return rules.findById(id);
    }

    public boolean toSetRules(Accident accident, HttpServletRequest req) {
        return rules.toSetRules(accident, req);
    }
}
