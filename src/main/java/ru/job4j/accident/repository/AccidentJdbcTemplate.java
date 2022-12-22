package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.rowmapper.AccidentMapper;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
@ThreadSafe
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;
    private final AccidentMapper accidentRowMapper;
    private final static String SQL_REQUEST_FIND_ALL = "select "
            + "accident.id, "
            + "accident.type_id, "
            + "accident.name, "
            + "accident.text, "
            + "accident.address, "
            + "accident.created, "
            + "type.type_name "
            + "from accident "
            + "inner join type on accident.type_id=type.type_id ";

    public int create(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into accident (type_id, name, text, address) values (?, ?, ?, ?)",
                    new String[] {"id"});
            ps.setInt(1, accident.getType().getId());
            ps.setString(2, accident.getName());
            ps.setString(3, accident.getText());
            ps.setString(4, accident.getAddress());
            return ps;
            },
            keyHolder);
        return keyHolder.getKey().intValue();
    }

    public Collection<Accident> findAll() {
        return jdbc.query(SQL_REQUEST_FIND_ALL,
                accidentRowMapper
        );
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(jdbc.queryForObject(SQL_REQUEST_FIND_ALL
                        + "where id=?",
                accidentRowMapper, id));
    }

    public Accident update(Accident accident) {
        jdbc.update("update accident set name=?, text=?, address=?,type_id= ?, created =? where id=?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                Timestamp.valueOf(LocalDateTime.now()),
                accident.getId());
        return accident;
    }
}