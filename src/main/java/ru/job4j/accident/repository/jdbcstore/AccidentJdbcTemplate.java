package ru.job4j.accident.repository.jdbcstore;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.jdbcstore.rowmapper.AccidentMapper;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
@ThreadSafe
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;
    private final AccidentMapper accidentRowMapper;

    public Accident create(Accident accident) {
        jdbc.update("insert into accident (type, name, text, address) values (?, ?, ?, ?)",
                accident.getType(),
                accident.getName(),
                accident.getText(),
                accident.getAddress());
        return accident;
    }

    public Collection<Accident> findAll() {
        return jdbc.query("select  distinct "
                        + "accident.id, "
                        + "accident.type, "
                        + "accident.name, "
                        + "rule.rule_id, "
                        + "accident.text, "
                        + "accident.address, "
                        + "accident.created, "
                        + "type.type_name, "
                        + "rule.rule_name, "
                        + "accident_rule.accident_id, "
                        + "accident_rule.rule_id "
                        + "from accident "
                        + "inner join type on accident.type=type.type_id "
                        + "inner join accident_rule on accident.id=accident_rule.accident_id "
                        + "inner join rule on accident_rule.rule_id=rule.rule_id ",
                accidentRowMapper
        );
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(jdbc.queryForObject("select * from accident LEFT OUTER JOIN accident_rule "
                + "ON accident.ID = accident_rule.accident_id "
                + "LEFT OUTER JOIN rule  ON accident_rule.rule_id = rule.ID where id=?", accidentRowMapper, id));
    }

    public Accident update(Accident accident) {
        jdbc.update("update accident set name=? where id=?",
                accident.getName(), accident.getId());
        return accident;
    }
}