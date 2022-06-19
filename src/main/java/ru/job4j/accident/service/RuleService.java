package ru.job4j.accident.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleMem;

import java.util.List;

@Service
@ThreadSafe
public class RuleService {
    private final RuleMem ruleMem;

    public RuleService(RuleMem ruleMem) {
        this.ruleMem = ruleMem;
    }

    public List<Rule> findAll() {
        return ruleMem.findAll();
    }

    public Rule findById(int id) {
        return ruleMem.findById(id);
    }
}
