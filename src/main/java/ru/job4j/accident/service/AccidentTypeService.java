package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeStore;

import java.util.Collection;
import java.util.Optional;

@Service
@ThreadSafe
@AllArgsConstructor
public class AccidentTypeService {
    private final AccidentTypeStore accidentTypeStore;

    public Collection<AccidentType> findAll() {
        return accidentTypeStore.findAll();
    }

    public Optional<AccidentType> get(int id) {
        return accidentTypeStore.get(id);
    }
}
