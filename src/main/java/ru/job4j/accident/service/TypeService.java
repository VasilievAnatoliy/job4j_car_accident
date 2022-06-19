package ru.job4j.accident.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.TypeMem;

import java.util.List;

@Service
@ThreadSafe
public class TypeService {
    private final TypeMem typeMem;

    public TypeService(TypeMem typeMem) {
        this.typeMem = typeMem;
    }

    public List<AccidentType> findAll() {
        return typeMem.findAll();
    }

    public AccidentType findById(int id) {
        return typeMem.findById(id);
    }
}
