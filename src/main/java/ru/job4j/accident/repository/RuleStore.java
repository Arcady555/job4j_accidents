package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.*;

@Repository
@AllArgsConstructor
@ThreadSafe
public class RuleStore {
    private final SessionFactory sf;

    public Collection<Rule> findAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule", Rule.class)
                    .list();
        }
    }

    public Map<Integer, Set<Rule>> findAllSets() {
        return null;

    }

    public Optional<Rule> findById(int id) {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule where rule_id=:fId", Rule.class)
                    .setParameter("fId", id)
                    .uniqueResultOptional();
        }
    }
}
