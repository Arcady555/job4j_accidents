package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
@ThreadSafe
public class AccidentService {
    private final AccidentJdbcTemplate accidents;

    public Collection<Accident> findAll() {
        return accidents.findAll();
    }

    public void create(Accident accident) {
        accidents.create(accident);
    }

    public Optional<Accident> findById(int id) {
        return accidents.findById(id);
    }

    public void update(Accident accident) {
        accidents.update(accident);
    }
}
