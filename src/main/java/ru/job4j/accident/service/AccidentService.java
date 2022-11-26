package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

@Service
@AllArgsConstructor
@ThreadSafe
public class AccidentService {
    private final AccidentMem accidentMem;

    public Collection<Accident> findAll() {
        return accidentMem.findAll();
    }

    public void create(Accident accident) {
        accidentMem.create(accident);
    }

    public Accident findById(int id) {
        return accidentMem.findById(id);
    }

    public void replace(Accident accident) {
        accidentMem.replace(accident);
    }
}
