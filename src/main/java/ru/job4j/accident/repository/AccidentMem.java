package ru.job4j.accident.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@ThreadSafe
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger count = new AtomicInteger(0);

    public AccidentMem() {
        addAccident(Accident.of(0, "name1", "text1", "address1"));
        addAccident(Accident.of(0, "name2", "text2", "address2"));
        addAccident(Accident.of(0, "name3", "text3", "address3"));
    }

    public Accident addAccident(Accident accident) {
        accident.setId(count.incrementAndGet());
        return accidents.put(accident.getId(), accident);
    }

    public List<Accident> findAll() {
        return accidents.values().stream().toList();
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public void update(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }
}
