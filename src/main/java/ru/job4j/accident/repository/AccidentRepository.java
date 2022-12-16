package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;

import java.util.List;
import java.util.Optional;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    @Override
    @EntityGraph(attributePaths = {"type", "rules"})
    List<Accident> findAll();

    @Override
    @EntityGraph(attributePaths = {"type", "rules"})
    Accident save(Accident accident);

    @EntityGraph(attributePaths = {"type", "rules"})
    Optional<Accident> findById(int id);
}
