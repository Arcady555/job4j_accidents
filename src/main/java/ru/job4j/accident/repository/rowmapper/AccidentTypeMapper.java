package ru.job4j.accident.repository.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.job4j.accident.model.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AccidentTypeMapper implements RowMapper<AccidentType> {

    @Override
    public AccidentType mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccidentType accidentType = new AccidentType();
        accidentType.setId(rs.getInt("type_id"));
        accidentType.setName(rs.getString("type_name"));
        return accidentType;
    }
}
