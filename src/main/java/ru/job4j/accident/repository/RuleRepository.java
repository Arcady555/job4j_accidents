package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Rule;

import java.util.Optional;

public interface RuleRepository extends CrudRepository<Rule, Integer> {
    @EntityGraph(attributePaths = {"accidents"})
    Iterable<Rule> findAll();

    @EntityGraph(attributePaths = {"accidents"})
    Optional<Rule> findById(int key);
}
