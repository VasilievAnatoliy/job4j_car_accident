package ru.job4j.accident.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*@Repository*/
@ThreadSafe
public class RuleMem {
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();

    public RuleMem() {
        rules.put(1, Rule.of(1, "Статья 1"));
        rules.put(2, Rule.of(2, "Статья 2"));
        rules.put(3, Rule.of(3, "Статья 3"));
        rules.put(4, Rule.of(4, "Статья 4"));
    }

    public List<Rule> findAll() {
        return new ArrayList<>(rules.values());
    }

    public Rule findById(int id) {
        return rules.get(id);
    }
}
