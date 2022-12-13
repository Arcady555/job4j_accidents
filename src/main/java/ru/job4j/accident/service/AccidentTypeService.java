package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.Map;

@Service
@ThreadSafe
public class AccidentTypeService {
    private final Map<Integer, AccidentType> types;

    public AccidentTypeService() {
        this.types = Map.of(
                1, new AccidentType(1, "Две машины"),
                2, new AccidentType(2, "Машина и человек"),
                3, new AccidentType(3, "Машина и велосипед")
        );
    }

    public Collection<AccidentType> findAll() {
        return types.values();
    }

    public AccidentType get(int id) {
        return types.get(id);
    }
}
