package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
@ThreadSafe
public class AccidentTypeStore {
    private final JdbcTemplate jdbc;

    public Collection<AccidentType> findAll() {
        return null;
    }

    public Optional<AccidentType> get(int id) {
        return null;
    }
}
