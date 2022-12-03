package ru.job4j.accident.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;

import java.util.List;

@Service
@ThreadSafe
public class AccidentTypeService {
    private final List<AccidentType> list;

    public AccidentTypeService() {
        this.list = List.of(
                new AccidentType(0, "Две машины"),
                new AccidentType(1, "Машина и человек"),
                new AccidentType(2, "Машина и велосипед")
        );
    }

    public List<AccidentType> findAll() {
        return list;
    }

    public AccidentType get(int id) {
        return list.get(id);
    }
}
