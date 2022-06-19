package ru.job4j.accident.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@ThreadSafe
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger count = new AtomicInteger(0);

    public AccidentMem() {
        addAccident(Accident.of(0, "Лобовое столкновение", "Описание столкновения",
                "address1", AccidentType.of(1, "Две машины"),
                Set.of(Rule.of(1, "Статья 1"))));
        addAccident(Accident.of(0, "Сбит человек на переходе", "Описание происшествия",
                "address2", AccidentType.of(2, "Машина и человек"),
                Set.of(Rule.of(2, "Статья 2"))));
        addAccident(Accident.of(0, "ДТП во дворе", "Описание ситуации",
                "address3", AccidentType.of(3, "Машина и велосипед"),
                Set.of(Rule.of(4, "Статья 4"), Rule.of(3, "Статья 3"))));
    }

    public Accident addAccident(Accident accident) {
        accident.setId(count.incrementAndGet());
        return accidents.put(accident.getId(), accident);
    }

    public List<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public void update(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }
}
