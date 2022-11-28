package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import net.jcip.annotations.ThreadSafe;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
@ThreadSafe
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public void create(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Optional<Accident> findById(int id) {
        Optional<Accident> rsl = Optional.empty();
        Accident accident = accidents.get(id);
        if (accident != null) {
            rsl = Optional.of(accident);
        }
        return rsl;
    }

    public void replace(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }
}