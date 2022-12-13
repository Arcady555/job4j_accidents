package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.repository.RuleStore;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
@ThreadSafe
public class AccidentService {
    private final AccidentJdbcTemplate accidents;
    private final RuleStore ruleStore;

    public int create(Accident accident) {
        return accidents.create(accident);
    }

    public Collection<Accident> findAll() {
        Collection<Accident> list = accidents.findAll();
        Map<Integer, Set<Rule>> allRuleSets = ruleStore.findAllSets();
        for (Accident accident : list) {
            accident.setRules(allRuleSets.get(accident.getId()));
        }
        return list;
    }

    public Optional<Accident> findById(int id) {
        Accident accident = accidents.findById(id).get();
        Map<Integer, Set<Rule>> allRuleSets = ruleStore.findAllSets();
        accident.setRules(allRuleSets.get(accident.getId()));
        return Optional.of(accident);
    }

    public Accident update(Accident accident) {
        return accidents.update(accident);
    }
}