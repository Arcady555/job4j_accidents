package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.jdbcstore.AccidentJdbcTemplate;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
@Service
@ThreadSafe
public class AccidentService {
    private final AccidentJdbcTemplate accidents;

    public Accident create(Accident accident) {
        return accidents.create(accident);
    }

    public Collection<Accident> findAll() {
        return accidents.findAll();
    }

    public Optional<Accident> findById(int id) {
        return accidents.findById(id);
    }

    public Accident update(Accident accident) {
        return accidents.update(accident);
    }
}