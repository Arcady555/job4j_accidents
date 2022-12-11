package ru.job4j.accident.repository.jdbcstore;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.jdbcstore.rowmapper.RuleMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Repository
@AllArgsConstructor
@ThreadSafe
public class RuleStore {
    private final JdbcTemplate jdbc;
    private final RuleMapper rowMapper;

    public Collection<Rule> findAll() {
        return jdbc.query("select * from rule", rowMapper);
    }

    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(jdbc.queryForObject("select * from rule where id=?", rowMapper, id));
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