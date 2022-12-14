package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
@ThreadSafe
public class AccidentService {
    private final AccidentRepository accidents;

    public Iterable<Accident> findAll() {
        return accidents.findAll();
    }

    public void save(Accident accident) {
        accidents.save(accident);
    }

    public Optional<Accident> findById(int id) {
        return accidents.findById(id);
    }
}