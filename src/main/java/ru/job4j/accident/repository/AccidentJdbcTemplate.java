package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.resultsetextractor.RuleExtractor;
import ru.job4j.accident.repository.rowmapper.AccidentMapper;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
@ThreadSafe
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;
    private final AccidentMapper accidentRowMapper;

    public Accident create(Accident accident) {
        jdbc.update("insert into accident (type_id, name, text, address) values (?, ?, ?, ?)",
                accident.getType().getId(),
                accident.getName(),
                accident.getText(),
                accident.getAddress());
        return accident;
    }

    public Collection<Accident> findAll() {
        return jdbc.query("select "
                        + "accident.id, "
                        + "accident.type_id, "
                        + "accident.name, "
                        + "accident.text, "
                        + "accident.address, "
                        + "accident.created, "
                        + "type.type_name "
                        + "from accident "
                        + "inner join type on accident.type_id=type.type_id ",
                accidentRowMapper
        );
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(jdbc.queryForObject("select "
                        + "accident.id, "
                        + "accident.type_id, "
                        + "accident.name, "
                        + "accident.text, "
                        + "accident.address, "
                        + "accident.created, "
                        + "type.type_name "
                        + "from accident "
                        + "inner join type on accident.type_id=type.type_id "
                        + "where id=?",
                accidentRowMapper, id));
    }

    public Accident update(Accident accident) {
        jdbc.update("update accident set name=? where id=?",
                accident.getName(), accident.getId());
        return accident;
    }
}