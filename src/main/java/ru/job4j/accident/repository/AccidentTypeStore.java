package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.rowmapper.AccidentTypeMapper;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
@ThreadSafe
public class AccidentTypeStore {
    private final JdbcTemplate jdbc;
    private final AccidentTypeMapper rowMapper;

    public Collection<AccidentType> findAll() {
        return jdbc.query("select * from type ", rowMapper);
    }

    public Optional<AccidentType> get(int id) {
            return Optional.ofNullable(jdbc
                    .queryForObject("select * from type where type_id=? ", rowMapper, id)
            );
    }
}
