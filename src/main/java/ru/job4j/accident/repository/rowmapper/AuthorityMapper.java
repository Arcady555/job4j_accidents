package ru.job4j.accident.repository.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.job4j.accident.model.Authority;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AuthorityMapper implements RowMapper<Authority> {
    @Override
    public Authority mapRow(ResultSet rs, int rowNum) throws SQLException {
        Authority authority = new Authority();
        authority.setId(rs.getInt("id"));
        authority.setAuthority(rs.getString("authority"));
        return authority;
    }
}
