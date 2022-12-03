package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.accident.model.AccidentType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@AllArgsConstructor
@ThreadSafe
public class AccidentMem {
    private final AtomicInteger atomicId = new AtomicInteger(0);
    private final Map<Integer, Accident> accidents = new HashMap<>();

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public void create(Accident accident) {
            accident.setId(atomicId.incrementAndGet());
            accidents.put(accident.getId(), accident);
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    public void update(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }
}
