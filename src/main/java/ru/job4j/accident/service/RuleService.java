package ru.job4j.accident.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleHibernate;
import ru.job4j.accident.repository.RuleJdbcTemplate;

import java.util.List;

/*@Service*/
@ThreadSafe
public class RuleService {
    private final RuleHibernate rules;

    public RuleService(RuleHibernate rules) {
        this.rules = rules;
    }

    public List<Rule> findAll() {
        return rules.getAll();
    }

    public Rule findById(int id) {
        return rules.findById(id);
    }
}
