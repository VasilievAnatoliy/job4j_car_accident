package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Type;

import java.util.List;

@Repository
public class TypeJdbcTemplate {
    public final JdbcTemplate jdbc;

    public TypeJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Type> getAll() {
        return jdbc.query("select id, name from type;",
                (rs, row) ->
                        Type.of(rs.getInt("id"),
                                rs.getString("name"))
        );
    }

    public Type findById(int id) {
        return jdbc.queryForObject("select id, name from type where id = ?",
                (rs, row) ->
                        Type.of(rs.getInt("id"),
                                rs.getString("name")), id);
    }
}
