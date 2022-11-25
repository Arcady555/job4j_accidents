package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Map;

@Service
@AllArgsConstructor
@ThreadSafe
public class AccidentService {
    private final AccidentMem accidentMem;

    public Map<Integer, Accident> findAll() {
        return accidentMem.findAll();
    }
}
