package ru.job4j.accident.repository.jdbcstore.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;

@Component
public class AccidentMapper implements RowMapper<Accident> {

    @Override
    public Accident mapRow(ResultSet rs, int rowNum) throws SQLException {
        Accident accident = new Accident();
        accident.setId(rs.getInt("id"));
        accident.setType(new AccidentType(rs.getInt("type"), rs.getString("type_name")));
        accident.setName(rs.getString("name"));
        accident.setText(rs.getString("text"));
        accident.setAddress(rs.getString("address"));
        accident.setCreated(rs.getTimestamp("created").toLocalDateTime().truncatedTo(ChronoUnit.SECONDS));
        return accident;
    }
}
