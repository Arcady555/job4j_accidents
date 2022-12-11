package ru.job4j.accident.repository.jdbcstore.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.job4j.accident.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RuleMapper implements RowMapper<Rule> {
    @Override
    public Rule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rule rule = new Rule();
        rule.setId(rs.getInt("id"));
        rule.setName(rs.getString("name"));
        return rule;
    }
}
