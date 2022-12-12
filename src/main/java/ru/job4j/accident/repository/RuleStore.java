package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.resultsetextractor.RuleExtractor;
import ru.job4j.accident.repository.rowmapper.RuleMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Repository
@AllArgsConstructor
@ThreadSafe
public class RuleStore {
    private final JdbcTemplate jdbc;
    private final RuleMapper rowMapper;
    private final RuleExtractor ruleExtractor;

    public Collection<Rule> findAll() {
        return jdbc.query("select * from rule", rowMapper);
    }

    public Map<Integer, Set<Rule>> findAllSets() {
        return jdbc.query("select  "
                        + "accident.id, "
                        + "rule.rule_id, "
                        + "rule.rule_name, "
                        + "accident_rule.accident_id, "
                        + "accident_rule.rule_id "
                        + "from accident "
                        + "inner join accident_rule on accident.id=accident_rule.accident_id "
                        + "inner join rule on accident_rule.rule_id=rule.rule_id ",
                ruleExtractor
        );

    }

    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(jdbc.queryForObject("select * from rule where rule_id=?", rowMapper, id));
    }

    public boolean toSetRules(Accident accident, HttpServletRequest req) {
        boolean rsl = false;
        Set<Rule> set = new HashSet<>();
        String[] ids = req.getParameterValues("rIds");
        if (ids != null) {
            for (String str : ids) {
                Optional<Rule> ruleOptional = findById(Integer.parseInt(str));
                if(ruleOptional.isEmpty()) {
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