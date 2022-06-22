package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.model.Type;

import java.util.List;

@Repository
public class RuleJdbcTemplate {
    public final JdbcTemplate jdbc;

    public RuleJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Rule> getAll() {
        return jdbc.query("select id, name from rule",
                (rs, row) ->
                        Rule.of(rs.getInt("id"),
                                rs.getString("name"))
        );
    }

    public Rule findById(int id) {
        return jdbc.queryForObject("select id, name from rule where id = ?",
                (rs, row) ->
                        Rule.of(rs.getInt("id"),
                                rs.getString("name")), id);
    }
}
