package ru.job4j.accident.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@ThreadSafe
public class TypeMem {
    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>();

    public TypeMem() {
        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));
    }

    public List<AccidentType> findAll() {
        return new ArrayList<>(types.values());
    }

    public AccidentType findById(int id) {
        return types.get(id);
    }
}
