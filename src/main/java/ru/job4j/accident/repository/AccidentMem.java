package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
@ThreadSafe
public class AccidentMem {
    Map<Integer, Accident> accidents = new HashMap<>();

    public Map<Integer, Accident> findAll() {
        return accidents;
    }

}
