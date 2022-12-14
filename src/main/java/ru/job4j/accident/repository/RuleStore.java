package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;

import javax.servlet.http.HttpServletRequest;
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
/*
    public boolean toSetRules(Accident accident, HttpServletRequest req) {
        boolean rsl = false;
        Set<Rule> set = new HashSet<>();
        String[] ids = req.getParameterValues("rIds");
        if (ids != null) {
            jdbc.update("delete from accident_rule where accident_id=?",
                    accident.getId());
            for (String str : ids) {
                int ruleId = Integer.parseInt(str);
                if (findById(ruleId).isEmpty()) {
                    return false;
                }
                jdbc.update("insert into accident_rule (accident_id, rule_id) values (?, ?)",
                        accident.getId(),
                        ruleId);
                rsl = true;
            }
        }
        accident.setRules(set);
        return rsl;
    } */
}
