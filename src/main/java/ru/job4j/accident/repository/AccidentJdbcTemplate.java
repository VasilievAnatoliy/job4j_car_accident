package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Type;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Set;

/*@Repository*/
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "insert into accident (name, text, address, type_id) values (?, ?, ?, ?)",
                            new String[]{"id"});
                    ps.setString(1, accident.getName());
                    ps.setString(2, accident.getText());
                    ps.setString(3, accident.getAddress());
                    ps.setInt(4, accident.getType().getId());
                    return ps;
                }, keyHolder
        );
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accident_rule(accident_id, rule_id) values(?, ?)",
                    keyHolder.getKey(),
                    rule.getId());
        }
        return accident;
    }

    public List<Accident> getAll() {
        return jdbc.query(
                "select a.id as id, a.name as name, a.text as text, a.address as address,"
                        + " type.id as typeId, type.name as typeName "
                        + "from accident a "
                        + "join type on a.type_id = type.id;",
                formGetAccident());
    }

    public Accident findById(int id) {
        return jdbc.queryForObject(
                "select a.id as id, a.name as name, a.text as text, a.address as address, "
                       + "type.id as typeId, type.name as typeName "
                       + "from accident a "
                       + "join type on a.type_id = type.id "
                       + "where a.id = ?",

                formGetAccident(), id
        );
    }

    private RowMapper<Accident> formGetAccident() {
       return (rs, row) -> {
            Accident accident = new Accident();
            accident.setId(rs.getInt("id"));
            accident.setName(rs.getString("name"));
            accident.setText(rs.getString("text"));
            accident.setAddress(rs.getString("address"));
            accident.setType(Type.of(rs.getInt("typeId"), rs.getString("typeName")));
            accident.setRules(Set.copyOf(getRuleByAccidentId(accident.getId())));
            return accident;
        };
    }

    private List<Rule> getRuleByAccidentId(int id) {
        return jdbc.query("select r.id as id, r.name as name from accident_rule ar "
                        + "join rule r on ar.rule_id = r.id where accident_id = ?",
                (rs, row) -> Rule.of(rs.getInt("id"),
                        rs.getString("name")), id
        );
    }

    public void update(Accident accident) {
        jdbc.update("delete from accident_rule where accident_id = ?",
                accident.getId());
        jdbc.update("update accident set name = ?, text = ?, address = ? "
                        + "where accident.id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getId()
        );
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accident_rule(accident_id, rule_id) values(?, ?)",
                    accident.getId(),
                    rule.getId());
        }
    }
}
