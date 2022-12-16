package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@ThreadSafe
public class AccidentService {
    private final AccidentRepository accidents;

    @EntityGraph(value = "Accident.rules")
    public List<Accident> findAll() {
        return accidents.findAll();
    }

    @EntityGraph(value = "Accident.rules")
    public void save(Accident accident) {
        accidents.save(accident);
    }

    @EntityGraph(value = "Accident.rules")
    public Optional<Accident> findById(int id) {
        return accidents.findById(id);
    }
}