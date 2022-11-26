package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import net.jcip.annotations.ThreadSafe;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
@ThreadSafe
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public void create(Accident accident) {

    }

    public Accident findById(int id) {
        return null;
    }

    public void replace(Accident accident) {

    }
}