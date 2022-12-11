package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.jdbcstore.AccidentTypeStore;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
@Service
@ThreadSafe
public class AccidentTypeService {
    private final AccidentTypeStore types;

    public Collection<AccidentType> findAll() {
        return types.findAll();
    }

    public Optional<AccidentType> get(int id) {
        return types.get(id);
    }
}
