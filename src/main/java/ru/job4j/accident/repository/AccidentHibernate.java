package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@ThreadSafe
public class AccidentHibernate {
    private final SessionFactory sf;

    public Accident create(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(accident);
            session.getTransaction().commit();
            return accident;
        }
    }

    public List<Accident> findAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("select distinct a from Accident a join fetch a.type join fetch a.rules ",
                            Accident.class)
                    .list();
        }
    }

    public Optional<Accident> findById(int id) {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("select distinct a from Accident a join fetch a.type join fetch a.rules where id=:fId",
                            Accident.class)
                    .setParameter("fId", id)
                    .uniqueResultOptional();
        }
    }

    public void update(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.merge(accident);
            session.getTransaction().commit();
        }
    }
}