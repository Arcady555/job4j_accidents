package ru.job4j.accident.repository.hibernatestore;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.accident.model.Accident;

import java.util.List;
import java.util.Optional;

//@Repository
@AllArgsConstructor
public class AccidentHibernate {
    private final SessionFactory sf;

    public Accident create(Accident accident) {
        try (Session session = sf.openSession()) {
            Transaction t = session.beginTransaction();
            session.save(accident);
            t.commit();
            return accident;
        }
    }

    public List<Accident> findAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Accident", Accident.class)
                    .list();
        }
    }

    public Optional<Accident> findById(int id) {
        try (Session session = sf.openSession()) {
            return Optional.ofNullable(session
                    .createQuery("from Accident where id=:fId", Accident.class)
                    .setParameter("fId", id)
                    .uniqueResult());
        }
    }

    public void update(Accident accident) {
        try (Session session = sf.openSession()) {
            Transaction t = session.beginTransaction();
            session.merge(accident);
            t.commit();
        }
    }
}