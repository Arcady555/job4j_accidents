package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.rowmapper.UserMapper;

@Repository
@AllArgsConstructor
@ThreadSafe
public class UserRepository {
    private final JdbcTemplate jdbc;
    private final UserMapper userMapper;

    public User save(User user) {
        jdbc.update("insert into users (username, password, enabled, authority_id) values (?, ?, ?, ?)",
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.getAuthority().getId());
        return user;
    }
}
