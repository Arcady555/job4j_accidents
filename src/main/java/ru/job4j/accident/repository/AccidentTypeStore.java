package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
@ThreadSafe
public class AccidentTypeStore {
    private final SessionFactory sf;

    public Collection<AccidentType> findAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType", AccidentType.class)
                    .list();
        }
    }

    public Optional<AccidentType> get(int id) {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType where id=:fId", AccidentType.class)
                    .setParameter("fId", id)
                    .uniqueResultOptional();
        }
    }
}
