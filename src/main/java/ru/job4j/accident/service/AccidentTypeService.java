package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeRepository;

import java.util.Optional;

@Service
@ThreadSafe
@AllArgsConstructor
public class AccidentTypeService {
    private final AccidentTypeRepository accidentTypeStore;

    public Iterable<AccidentType> findAll() {
        return accidentTypeStore.findAll();
    }

    public Optional<AccidentType> findById(int id) {
        return accidentTypeStore.findById(id);
    }
}
