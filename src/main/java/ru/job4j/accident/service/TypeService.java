package ru.job4j.accident.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Type;
import ru.job4j.accident.repository.TypeJdbcTemplate;

import java.util.List;

@Service
@ThreadSafe
public class TypeService {
    private final TypeJdbcTemplate types;

    public TypeService(TypeJdbcTemplate types) {
        this.types = types;
    }

    public List<Type> findAll() {
        return types.getAll();
    }

    public Type findById(int id) {
        return types.findById(id);
    }
}
