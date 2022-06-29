package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleServiceData {
    private final RuleRepository rules;

    public RuleServiceData(RuleRepository rules) {
        this.rules = rules;
    }

    public List<Rule> findAll() {
        List<Rule> res = new ArrayList<>();
        rules.findAll().forEach(res::add);
        return res;
    }

    public Rule findById(int id) {
        return rules.findById(id).get();
    }

}
