package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Authority;
import ru.job4j.accident.repository.rowmapper.AuthorityMapper;

@Repository
@AllArgsConstructor
@ThreadSafe
public class AuthorityRepository {
    private final JdbcTemplate jdbc;
    private final AuthorityMapper authorityMapper;

    public Authority findByAuthority(String authority) {
        return jdbc.queryForObject("select * from authorities where authority = ?",
                authorityMapper,
                authority);
    }
}
