package ru.job4j.accident.repository.resultsetextractor;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import ru.job4j.accident.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class RuleExtractor implements ResultSetExtractor<Map<Integer, Set<Rule>>> {
    @Override
    public Map<Integer, Set<Rule>> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, Set<Rule>> allRules = new HashMap<>();
        while (rs.next()) {
            int accidentId = rs.getInt("accident_id");
            allRules.putIfAbsent(accidentId, new HashSet<>());
            Rule rule = new Rule();
            rule.setId(rs.getInt("rule_id"));
            rule.setName(rs.getString("rule_name"));
            allRules.get(accidentId).add(rule);
        }
        return allRules;
    }
}
